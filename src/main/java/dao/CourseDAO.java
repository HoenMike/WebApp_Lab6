/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author hoang
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Course;
import util.DBConnection;

public class CourseDAO {

    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM course";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("CourseID"));
                course.setName(rs.getString("CourseName"));
                courses.add(course);
            }
        }
        return courses;
    }

    public Course getCourse(int id) throws SQLException {
        String sql = "SELECT * FROM course WHERE CourseID = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Course course = new Course();
                    course.setId(rs.getInt("CourseID"));
                    course.setName(rs.getString("CourseName"));
                    return course;
                }
            }
        }
        return null;
    }

    public void addCourse(Course course) throws SQLException {
        String sql = "INSERT INTO course (CourseName) VALUES (?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, course.getName());
            stmt.executeUpdate();
        }
    }

    public void updateCourse(Course course) throws SQLException {
        String sql = "UPDATE course SET CourseName = ? WHERE CourseID = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, course.getName());
            stmt.setInt(2, course.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteCourse(int id) throws SQLException {
        String sql = "DELETE FROM course WHERE CourseID = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
