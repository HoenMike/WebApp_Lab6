package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.Student;
import util.DBConnection;

public class StudentDAO {

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("StudentID"));
                student.setName(rs.getString("StudentName"));
                students.add(student);
            }
        }
        return students;
    }

    public Student getStudent(int id) throws SQLException {
        String sql = "SELECT * FROM student WHERE StudentID = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("StudentID"));
                    student.setName(rs.getString("StudentName"));
                    return student;
                }
            }
        }
        return null;
    }

    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO student (StudentName) VALUES (?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.executeUpdate();
        }
    }

    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE student SET StudentName = ? WHERE StudentID = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM student WHERE StudentID = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Course> getRegisteredCourses(int studentId) throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.CourseID, c.CourseName FROM course c "
                + "JOIN studentcourse sc ON c.CourseID = sc.CourseID "
                + "WHERE sc.StudentID = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Course course = new Course();
                    course.setId(rs.getInt("CourseID"));
                    course.setName(rs.getString("CourseName"));
                    courses.add(course);
                }
            }
        }
        return courses;
    }

    public List<Course> getAvailableCourses(int studentId) throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM course WHERE CourseID NOT IN "
                + "(SELECT CourseID FROM studentcourse WHERE StudentID = ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Course course = new Course();
                    course.setId(rs.getInt("CourseID"));
                    course.setName(rs.getString("CourseName"));
                    courses.add(course);
                }
            }
        }
        return courses;
    }

    public void addCourseRegistration(int studentId, int courseId) throws SQLException {
        String sql = "INSERT INTO studentcourse (StudentID, CourseID) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.executeUpdate();
        }
    }

    public void removeCourseRegistration(int studentId, int courseId) throws SQLException {
        String sql = "DELETE FROM studentcourse WHERE StudentID = ? AND CourseID = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.executeUpdate();
        }
    }
}
