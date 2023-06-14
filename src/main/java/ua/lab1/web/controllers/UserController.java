package ua.lab1.web.controllers;

import ua.lab1.web.exceptions.KeycloakSecurityServiceException;
import ua.lab1.web.exceptions.UserValidatorException;
import ua.lab1.web.services.UserService;
import ua.lab1.web.validators.CourseGradeValidator;
import ua.lab1.web.validators.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserController extends AbstractController {

    private static final UserService userService = new UserService();
    private static final UserValidator userValidator = new UserValidator();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            processRequest(req, resp);
            if (getMapping("/add-role")) {
                logger.info("trying to change role");
                String role =  req.getParameter("role");
                System.out.println(role);
                //String userId = req.getParameter("userId");
                String userId = (String) req.getAttribute("userId");
                System.out.println(userId);
                String fullName = (String) req.getAttribute("fullName");
                System.out.println(fullName);
                // validation
                if (!("Teacher".equals(role) || "Student".equals(role))) {
                    logger.info("Invalid parameter role");
                    resp.sendError(400, "Invalid parameter role");
                    return;
                }
                try {
                    userValidator.validateUserId(userId);
                    userValidator.validateFullName(fullName);
                } catch (UserValidatorException e) {
                    logger.info(e.getMessage());
                    resp.sendError(400, e.getMessage());
                    return;
                }

                boolean success = true;
                try {
                    System.out.println("calling userService");
                    userService.addRole(userId, fullName, role);
                } catch (KeycloakSecurityServiceException | SQLException e) {
                    logger.error(e.getMessage());
                    success = false;
                }

                if (success) {
                    logger.info("added role to user " + userId);
                    resp.setStatus(200);
                    this.responseOut.flush();
                }
                else {
                    logger.error("Error when adding role to user " + userId);
                    resp.setContentType("text/html");
                    resp.sendError(500, "Error when adding role to user");
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            resp.setStatus(500);
        }
        this.responseOut.flush();
    }
}
