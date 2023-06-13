package ua.lab1.web.dao;

import ua.lab1.web.enitities.Course;
import ua.lab1.web.exceptions.CourseDAOException;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {
    Integer create(String name, String teacherId, int maxGrade) throws SQLException, CourseDAOException;
    List<Course> getTeacherCourses(String teacherUserId) throws SQLException;
    List<Course> getStudentCourses(String studentUserId) throws SQLException;

    Course getById(Integer id) throws SQLException;
}
