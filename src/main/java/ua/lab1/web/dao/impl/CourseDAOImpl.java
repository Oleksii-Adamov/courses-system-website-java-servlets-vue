package ua.lab1.web.dao.impl;

import ua.lab1.web.dao.CourseDAO;
import ua.lab1.web.database.TransactionFactory;
import ua.lab1.web.enitities.Course;
import ua.lab1.web.exceptions.CourseDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public Integer create(String name, String teacherId, int maxGrade) throws SQLException, CourseDAOException {
        PreparedStatement preparedStatement = TransactionFactory.getInstance().getConnection()
                .preparedStatement("INSERT INTO public.\"COURSES\" (name, teacher_id, max_grade) VALUES(?, ?, ?);",
                        Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, teacherId);
        preparedStatement.setInt(3, maxGrade);
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        else {
            throw new CourseDAOException("Didn't get created course id");
        }
    }

    @Override
    public List<Course> getTeacherCourses(String teacherUserId) throws SQLException {
        PreparedStatement preparedStatement = TransactionFactory.getInstance().getConnection()
                .preparedStatement("SELECT c.id, c.name, c.teacher_id, c.max_grade FROM public.\"COURSES\" c " +
                        "WHERE c.teacher_id = ?");
        preparedStatement.setString(1, teacherUserId);
        ResultSet rs = preparedStatement.executeQuery();
        return coursesFromResultSet(rs);
    }

    @Override
    public List<Course> getStudentCourses(String studentUserId) throws SQLException {
        PreparedStatement preparedStatement = TransactionFactory.getInstance().getConnection()
                .preparedStatement("SELECT c.id, c.name, c.teacher_id, c.max_grade FROM public.\"COURSES\" c " +
                        "INNER JOIN public.\"STUDENTS_COURSES\" sc ON c.id = sc.courses_id " +
                        "INNER JOIN public.\"STUDENTS\" s ON sc.students_user_id = s.user_id " +
                        "WHERE s.user_id = ?");
        preparedStatement.setString(1, studentUserId);
        ResultSet rs = preparedStatement.executeQuery();
        return coursesFromResultSet(rs);
    }

    @Override
    public Course getById(Integer id) throws SQLException {
        PreparedStatement preparedStatement = TransactionFactory.getInstance().getConnection()
                .preparedStatement("SELECT c.id, c.name, c.teacher_id, c.max_grade FROM public.\"COURSES\" c " +
                        "WHERE c.id = ?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            Course course = new Course();
            course.setId(rs.getInt(1));
            course.setName(rs.getString(2));
            course.setTeacherId(rs.getString(3));
            course.setMaxGrade(rs.getInt(4));
            return course;
        }
        else {
            return null;
        }
    }

    private List<Course> coursesFromResultSet(ResultSet rs) throws SQLException {
        List<Course> courses = new ArrayList<>();
        while (rs.next()) {
            Course course = new Course();
            course.setId(rs.getInt(1));
            course.setName(rs.getString(2));
            course.setTeacherId(rs.getString(3));
            course.setMaxGrade(rs.getInt(4));
            courses.add(course);
        }
        return courses;
    }

}
