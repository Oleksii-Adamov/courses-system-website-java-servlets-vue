package ua.lab1.web.dao;

import ua.lab1.web.enitities.Teacher;
import ua.lab1.web.exceptions.TransactionException;

import java.sql.SQLException;

public interface TeacherDAO {
    void create(Teacher teacher) throws SQLException, TransactionException;
}
