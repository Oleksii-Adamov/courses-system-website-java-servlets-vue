package ua.lab1.web.dao;

import ua.lab1.web.dao.impl.StudentDAOImpl;
import ua.lab1.web.dao.impl.TeacherDAOImpl;

public class DAOFactory {
    private static final DAOFactory daoFactory = new DAOFactory();

    private DAOFactory(){}

    private final TeacherDAO teacherDAO = new TeacherDAOImpl();
    private final StudentDAO studentDAO = new StudentDAOImpl();

    public static DAOFactory getInstance(){
        return daoFactory;
    }

    public TeacherDAO getTeacherDAO(){
        return teacherDAO;
    }

    public StudentDAO getStudentDAO() { return studentDAO; }

}
