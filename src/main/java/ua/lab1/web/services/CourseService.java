package ua.lab1.web.services;

import ua.lab1.web.dao.DAOFactory;
import ua.lab1.web.database.TransactionFactory;
import ua.lab1.web.enitities.Course;
import ua.lab1.web.enitities.Student;
import ua.lab1.web.exceptions.CourseDAOException;
import ua.lab1.web.exceptions.TransactionException;
import ua.lab1.web.dto.StudentGradeDTO;

import java.sql.SQLException;
import java.util.List;

public class CourseService {
    public Integer create(String name, String teacherId, int maxGrade) throws SQLException, TransactionException, CourseDAOException {
        Integer id;
        TransactionFactory.getInstance().beginTransaction();
        try {
            id = DAOFactory.getInstance().getCourseDAO().create(name, teacherId, maxGrade);
        } catch (Exception e) {
            TransactionFactory.getInstance().rollbackTransaction();
            throw e;
        }
        TransactionFactory.getInstance().endTransaction();
        return id;
    }
    public List<Course> getTeacherCourses(String teacherUserId) throws SQLException, TransactionException {
        List<Course> courses;
        TransactionFactory.getInstance().beginTransaction();
        try {
            courses = DAOFactory.getInstance().getCourseDAO().getTeacherCourses(teacherUserId);
        } catch (Exception e) {
            TransactionFactory.getInstance().rollbackTransaction();
            throw e;
        }
        TransactionFactory.getInstance().endTransaction();
        return courses;
    }
    public List<Course> getStudentCourses(String studentUserId) throws SQLException, TransactionException {
        List<Course> courses;
        TransactionFactory.getInstance().beginTransaction();
        try {
            courses = DAOFactory.getInstance().getCourseDAO().getStudentCourses(studentUserId);
        } catch (Exception e) {
            TransactionFactory.getInstance().rollbackTransaction();
            throw e;
        }
        TransactionFactory.getInstance().endTransaction();
        return courses;
    }

    public void joinCourse(String studentUserId, Integer courseId) throws SQLException, TransactionException {
        TransactionFactory.getInstance().beginTransaction();
        try {
            if (DAOFactory.getInstance().getCourseDAO().getById(courseId) != null &&
                    DAOFactory.getInstance().getStudentDAO().getByUserId(studentUserId) != null) {
                DAOFactory.getInstance().getStudentDAO().joinCourse(studentUserId, courseId);
            }
        } catch (Exception e) {
            TransactionFactory.getInstance().rollbackTransaction();
            throw e;
        }
        TransactionFactory.getInstance().endTransaction();
    }

    public List<Student> getCourseStudents(Integer courseId) throws SQLException, TransactionException {
        List<Student> students;
        TransactionFactory.getInstance().beginTransaction();
        try {
            students = DAOFactory.getInstance().getCourseDAO().getStudents(courseId);
        } catch (Exception e) {
            TransactionFactory.getInstance().rollbackTransaction();
            throw e;
        }
        TransactionFactory.getInstance().endTransaction();
        return students;
    }

    public void gradeStudent(Integer courseId, String studentUserId, Integer grade, String teacherResponse) throws SQLException, TransactionException {
        TransactionFactory.getInstance().beginTransaction();
        try {
            DAOFactory.getInstance().getCourseDAO().gradeStudent(courseId, studentUserId, grade, teacherResponse);
        } catch (Exception e) {
            TransactionFactory.getInstance().rollbackTransaction();
            throw e;
        }
        TransactionFactory.getInstance().endTransaction();
    }

    public StudentGradeDTO getStudentGrade(String studentUserId, Integer courseId) throws SQLException, TransactionException {
        StudentGradeDTO studentGradeDTO;
        TransactionFactory.getInstance().beginTransaction();
        try {
            studentGradeDTO = DAOFactory.getInstance().getStudentDAO().getStudentGrade(studentUserId, courseId);
        } catch (Exception e) {
            TransactionFactory.getInstance().rollbackTransaction();
            throw e;
        }
        TransactionFactory.getInstance().endTransaction();
        return studentGradeDTO;
    }
}
