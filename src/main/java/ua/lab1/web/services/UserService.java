package ua.lab1.web.services;

import ua.lab1.web.dao.DAOFactory;
import ua.lab1.web.database.TransactionFactory;
import ua.lab1.web.enitities.Student;
import ua.lab1.web.enitities.Teacher;
import ua.lab1.web.exceptions.KeycloakSecurityServiceException;
import ua.lab1.web.exceptions.TransactionException;
import ua.lab1.web.security.KeycloakSecurityService;
import ua.lab1.web.security.SecurityService;

import java.sql.SQLException;

public class UserService {
    private static final SecurityService securityService = new KeycloakSecurityService();
    public void addRole(String userId, String fullName, String role) throws SQLException, KeycloakSecurityServiceException, TransactionException {
        if ("Teacher".equals(role)) {
            TransactionFactory.getInstance().beginTransaction();
            try {
                DAOFactory.getInstance().getTeacherDAO().create(new Teacher(userId, fullName));
                securityService.giveUserTeacherRole(userId);
            } catch (KeycloakSecurityServiceException | SQLException e) {
                TransactionFactory.getInstance().rollbackTransaction();
                throw e;
            }
            TransactionFactory.getInstance().endTransaction();
        } else {
            TransactionFactory.getInstance().beginTransaction();
            try {
                DAOFactory.getInstance().getStudentDAO().create(new Student(userId, fullName));
                securityService.giveUserStudentRole(userId);
            } catch (KeycloakSecurityServiceException | SQLException e) {
                TransactionFactory.getInstance().rollbackTransaction();
                throw e;
            }
            TransactionFactory.getInstance().endTransaction();
        }
    }
}
