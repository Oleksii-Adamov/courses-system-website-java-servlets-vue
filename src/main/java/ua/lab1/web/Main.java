package ua.lab1.web;

import ua.lab1.web.dao.DAOFactory;
import ua.lab1.web.database.TransactionFactory;
import ua.lab1.web.enitities.Teacher;
import ua.lab1.web.exceptions.TransactionException;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            TransactionFactory.getInstance().beginTransaction();
            DAOFactory.getInstance().getTeacherDAO().create(new Teacher("6fd45d53-4296-4f53-b34e-b62356c6dc8a", "teacher_test", "teacher_test"));
            TransactionFactory.getInstance().endTransaction();
        } catch (SQLException | TransactionException e) {
            e.printStackTrace();
        }
    }
}