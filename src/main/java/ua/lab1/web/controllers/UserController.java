package ua.lab1.web.controllers;

import com.google.gson.Gson;
import ua.lab1.web.enitities.Course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UserController extends AbstractController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            processRequest(req, resp);
            if (getMapping("/add-role")) {
                logger.info("trying to change role");
                String role =  req.getParameter("role");
                String userId = req.getParameter("userId");
                boolean success = false;
                if (role != null && userId != null) {
                    if ("Teacher".equals(role)) {
                        success = securityService.giveUserTeacherRole(userId);
                    } else if ("Student".equals(role)) {
                        success = securityService.giveUserStudentRole(userId);
                    }
                }
                if (success) {
                    logger.info("add role to user " + userId);
                }
                else {
                    logger.error("error when adding role to user" + userId);
                }
                resp.sendRedirect("http://localhost:8081/");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        this.out.flush();
    }
}
