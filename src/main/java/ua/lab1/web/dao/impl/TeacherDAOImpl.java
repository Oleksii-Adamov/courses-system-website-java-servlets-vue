package ua.lab1.web.dao.impl;

import ua.lab1.web.dao.TeacherDAO;
import ua.lab1.web.database.TransactionFactory;
import ua.lab1.web.enitities.Teacher;
import ua.lab1.web.exceptions.TransactionException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeacherDAOImpl implements TeacherDAO {
    @Override
    public void create(Teacher teacher) throws SQLException, TransactionException {
        PreparedStatement preparedStatement = TransactionFactory.getInstance().getConnection()
                .preparedStatement("INSERT INTO public.\"TEACHERS\" (user_id, full_name) VALUES(?, ?);");
        preparedStatement.setString(1, teacher.getUserId());
        preparedStatement.setString(2, teacher.getFullName());
        preparedStatement.execute();
    }
}
