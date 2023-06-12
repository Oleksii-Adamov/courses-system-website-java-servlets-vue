package ua.lab1.web.dao.impl;

import ua.lab1.web.dao.StudentDAO;
import ua.lab1.web.database.TransactionFactory;
import ua.lab1.web.enitities.Student;
import ua.lab1.web.enitities.Teacher;
import ua.lab1.web.exceptions.TransactionException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public void create(Student student) throws SQLException, TransactionException {
        PreparedStatement preparedStatement = TransactionFactory.getInstance().getConnection()
                .preparedStatement("INSERT INTO public.\"STUDENTS\" (user_id, full_name) VALUES(?, ?);");
        preparedStatement.setString(1, student.getUserId());
        preparedStatement.setString(2, student.getFullName());
        preparedStatement.execute();
    }
}
