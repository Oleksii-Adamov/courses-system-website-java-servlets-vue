package ua.lab1.web.dao;

import ua.lab1.web.enitities.Student;
import ua.lab1.web.supplementary_entities.StudentGrade;

import java.sql.SQLException;

public interface StudentDAO {
    void create(Student student) throws SQLException;
    void joinCourse(String studentUserId, Integer courseId) throws SQLException;

    Student getByUserId(String userId) throws SQLException;

    StudentGrade getStudentGrade(String studentUserId, Integer courseId) throws SQLException;
}
