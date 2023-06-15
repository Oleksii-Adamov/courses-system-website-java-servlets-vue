package ua.lab1.web.dao.impl;

import ua.lab1.web.dao.StudentDAO;
import ua.lab1.web.database.TransactionFactory;
import ua.lab1.web.enitities.Student;
import ua.lab1.web.dto.StudentGradeDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public void create(Student student) throws SQLException {
        PreparedStatement preparedStatement = TransactionFactory.getInstance().getConnection()
                .preparedStatement("INSERT INTO public.\"STUDENTS\" (user_id, full_name) VALUES(?, ?);");
        preparedStatement.setString(1, student.getUserId());
        preparedStatement.setString(2, student.getFullName());
        preparedStatement.execute();
    }

    @Override
    public void joinCourse(String studentUserId, Integer courseId) throws SQLException {
        PreparedStatement preparedStatement = TransactionFactory.getInstance().getConnection()
                .preparedStatement("INSERT INTO public.\"STUDENTS_COURSES\" (STUDENTS_user_id, COURSES_id) VALUES(?, ?);");
        preparedStatement.setString(1, studentUserId);
        preparedStatement.setInt(2, courseId);
        preparedStatement.execute();
    }

    public Student getByUserId(String userId) throws SQLException {
        PreparedStatement preparedStatement = TransactionFactory.getInstance().getConnection()
                .preparedStatement("SELECT s.user_id, s.full_name FROM public.\"STUDENTS\" s " +
                        "WHERE s.user_id = ?");
        preparedStatement.setString(1, userId);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            Student student = new Student();
            student.setUserId(rs.getString(1));
            student.setFullName(rs.getString(2));
            return student;
        }
        else {
            return null;
        }
    }

    public StudentGradeDTO getStudentGrade(String studentUserId, Integer courseId) throws SQLException {
        PreparedStatement preparedStatement = TransactionFactory.getInstance().getConnection()
                .preparedStatement("SELECT sc.grade, sc.teacher_response, c.max_grade FROM public.\"COURSES\" c " +
                        "INNER JOIN public.\"STUDENTS_COURSES\" sc ON c.id = sc.courses_id " +
                        "WHERE sc.students_user_id = ? AND c.id = ?");
        preparedStatement.setString(1, studentUserId);
        preparedStatement.setInt(2, courseId);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            StudentGradeDTO studentGradeDTO = new StudentGradeDTO();
            Object gradeObj = rs.getObject(1);
            if (gradeObj == null) {
                studentGradeDTO.setGrade(null);
            }
            else {
                studentGradeDTO.setGrade((Integer) gradeObj);
            }
            studentGradeDTO.setTeacherResponse(rs.getString(2));
            studentGradeDTO.setMaxGrade(rs.getInt(3));
            return studentGradeDTO;
        }
        else {
            return null;
        }
    }
}
