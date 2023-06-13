package ua.lab1.web.controllers;

import com.google.gson.Gson;
import ua.lab1.web.services.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CoursesController extends AbstractController {

    private static final CourseService courseService = new CourseService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            processRequest(req, resp);
            String userId = (String) req.getAttribute("userId");
            if (getMapping("/student-courses")) {
                String coursesJsonString = new Gson().toJson(courseService.getStudentCourses(userId));
                this.responseOut.print(coursesJsonString);
                logger.info("response from /student-courses :" + coursesJsonString);
            }
            else if (getMapping("/teacher-courses")) {
                String coursesJsonString = new Gson().toJson(courseService.getTeacherCourses(userId));
                this.responseOut.print(coursesJsonString);
                logger.info("response from /teacher-courses :" + coursesJsonString);
            }
            else {
                logger.info("No such path " + req.getRequestURI());
                resp.sendError(404, "No such path " + req.getRequestURI());
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            resp.sendError(500, e.getMessage());
        }
        this.responseOut.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            processRequest(req, resp);
            String userId = (String) req.getAttribute("userId");
            if (getMapping("/create")) {
                String name = req.getParameter("name");
                String maxGradeStr = req.getParameter("maxGrade");
                Integer maxGrade;
                if (name == null) {
                    resp.sendError(400, "No parameter name");
                    logger.info("No parameter name");
                    return;
                }
                if (maxGradeStr == null) {
                    resp.sendError(400, "No parameter maxGradeStr");
                    logger.info("No parameter maxGradeStr");
                    return;
                }
                try {
                    maxGrade = Integer.parseInt(maxGradeStr);
                } catch (NumberFormatException e) {
                    resp.sendError(400, "Invalid parameter maxGradeStr");
                    logger.info("Invalid parameter maxGradeStr");
                    return;
                }
                Integer createdId = courseService.create(name, userId, maxGrade);
                this.responseOut.print(String.format("{id: %d}", createdId));
            }
            else if (getMapping("/join")) {
                String courseIdStr = req.getParameter("id");
                if (courseIdStr == null) {
                    resp.sendError(400, "No parameter id");
                    logger.info("No parameter id");
                    return;
                }
                Integer courseId;
                try {
                    courseId = Integer.parseInt(courseIdStr);
                } catch (NumberFormatException e) {
                    resp.sendError(400, "Invalid parameter id");
                    logger.info("Invalid parameter id");
                    return;
                }
                courseService.joinCourse(userId, courseId);
            }
            else {
                logger.info("No such path " + req.getRequestURI());
                resp.sendError(404, "No such path " + req.getRequestURI());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            resp.sendError(500, e.getMessage());
        }
        this.responseOut.flush();
    }
}
