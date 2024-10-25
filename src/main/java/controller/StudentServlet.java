/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;
import model.Student;

/**
 *
 * @author hoang
 */
@WebServlet(name = "StudentServlet", urlPatterns = {"/StudentServlet"})
public class StudentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private StudentDAO studentDAO;

    @Override
    public void init() {
        studentDAO = new StudentDAO();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "list":
                    listStudents(request, response);
                    break;
                case "view":
                    viewStudent(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteStudent(request, response);
                    break;
                default:
                    listStudents(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "view":
                    int studentId = Integer.parseInt(request.getParameter("studentID"));
                    viewStudentDetails(studentId, request, response);
                    break;
                case "add":
                    addStudent(request, response);
                    break;
                case "update":
                    updateStudent(request, response);
                    break;
                case "addCourse":
                    addCourseRegistration(request, response);
                    break;
                case "removeCourse":
                    removeCourseRegistration(request, response);
                    break;
                default:
                    response.sendRedirect("Student");
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Student> students = studentDAO.getAllStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("studentList.jsp").forward(request, response);
    }

    private void viewStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("studentId"));
        viewStudentDetails(id, request, response);
    }

    private void viewStudentDetails(int studentId, HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Student student = studentDAO.getStudent(studentId);
        if (student != null) {
            List<Course> registeredCourses = studentDAO.getRegisteredCourses(studentId);
            List<Course> availableCourses = studentDAO.getAvailableCourses(studentId);

            request.setAttribute("student", student);
            request.setAttribute("registeredCourses", registeredCourses);
            request.setAttribute("availableCourses", availableCourses);
            request.getRequestDispatcher("studentDetails.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        Student student = new Student();
        student.setName(name);
        studentDAO.addStudent(student);
        response.sendRedirect("Student");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        studentDAO.updateStudent(student);
        response.sendRedirect("Student");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        response.sendRedirect("Student");
    }

    private void addCourseRegistration(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        studentDAO.addCourseRegistration(studentId, courseId);
        response.sendRedirect("Student?action=view&studentId=" + studentId);
    }

    private void removeCourseRegistration(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        studentDAO.removeCourseRegistration(studentId, courseId);
        response.sendRedirect("Student?action=view&studentId=" + studentId);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("studentId"));
        Student student = studentDAO.getStudent(id);
        request.setAttribute("student", student);
        request.getRequestDispatcher("studentForm.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
