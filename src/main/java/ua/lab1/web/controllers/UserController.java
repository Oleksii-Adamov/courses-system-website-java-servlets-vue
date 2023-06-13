package ua.lab1.web.controllers;

import com.google.gson.Gson;
import ua.lab1.web.enitities.Course;
import ua.lab1.web.exceptions.KeycloakSecurityServiceException;
import ua.lab1.web.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController extends AbstractController {

    private static final UserService userService = new UserService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            processRequest(req, resp);
            if (getMapping("/add-role")) {
                logger.info("trying to change role");
                String role =  req.getParameter("role");
                //String userId = req.getParameter("userId");
                String userId = (String) req.getAttribute("userId");
                String fullName = (String) req.getAttribute("fullName");
                // validation
                if (!("Teacher".equals(role) || "Student".equals(role))) {
                    resp.sendError(400, "Invalid parameter role");
                }
                else if (userId == null || userId.length() != 36 || fullName == null) {
                    resp.sendError(500, "Something wrong with userId or fullName");
                }
                else {
                    boolean success = true;
                    try {
                        userService.addRole(userId, fullName, role);
                    } catch (KeycloakSecurityServiceException | SQLException e) {
                        logger.error(e.getMessage());
                        success = false;
                    }

                    if (success) {
                        logger.info("added role to user " + userId);
                        resp.sendRedirect("http://localhost:8081/");
                    }
                    else {
                        resp.setContentType("text/html");
                        resp.sendError(500, "Error when adding role to user");
                    }

                }

            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            resp.setStatus(500);
        }
        this.out.flush();
    }
}
