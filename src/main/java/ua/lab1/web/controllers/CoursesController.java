package ua.lab1.web.controllers;

import com.google.gson.Gson;
import ua.lab1.web.exceptions.CourseGradeValidatorException;
import ua.lab1.web.exceptions.CourseValidatorException;
import ua.lab1.web.exceptions.UserValidatorException;
import ua.lab1.web.services.CourseService;
import ua.lab1.web.validators.CourseGradeValidator;
import ua.lab1.web.validators.CourseValidator;
import ua.lab1.web.validators.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CoursesController extends AbstractController {

    private static final CourseService courseService = new CourseService();
    private static final CourseValidator courseValidator = new CourseValidator();
    private static final UserValidator userValidator = new UserValidator();
    private static final CourseGradeValidator courseGradeValidator = new CourseGradeValidator();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            processRequest(req, resp);
            String userId = (String) req.getAttribute("userId");
            String jsonResponse = "";
            if (requestMapping("/student-courses")) {
                jsonResponse = new Gson().toJson(courseService.getStudentCourses(userId));
                this.responseOut.print(jsonResponse);
            }
            else if (requestMapping("/teacher-courses")) {
                jsonResponse = new Gson().toJson(courseService.getTeacherCourses(userId));
                this.responseOut.print(jsonResponse);
            }
            else if (requestMapping("/course-students")) {
                Integer courseId;
                try {
                    courseId = courseValidator.getValidatedCourseId(req.getParameter("courseId"));
                }
                catch (CourseValidatorException e) {
                    logger.info(e.getMessage());
                    resp.sendError(400, e.getMessage());
                    return;
                }
                jsonResponse = new Gson().toJson(courseService.getCourseStudents(courseId));
                this.responseOut.print(jsonResponse);
            }
            else if (requestMapping("/student-grade")) {
                Integer courseId;
                try {
                    courseId = courseValidator.getValidatedCourseId(req.getParameter("courseId"));
                }
                catch (CourseValidatorException | UserValidatorException e) {
                    logger.info(e.getMessage());
                    resp.sendError(400, e.getMessage());
                    return;
                }
                jsonResponse = new Gson().toJson(courseService.getStudentGrade(userId, courseId));
                this.responseOut.print(jsonResponse);
            }
            else {
                logger.info("No such path " + req.getRequestURI());
                resp.sendError(404, "No such path " + req.getRequestURI());
                return;
            }
            logger.info("response from " + req.getRequestURI() + " " + jsonResponse);
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
            if (requestMapping("/create")) {
                String name = req.getParameter("name");
                Integer maxGrade;
                try {
                    courseValidator.validateName(name);
                    maxGrade = courseValidator.getValidatedMaxGrade(req.getParameter("maxGrade"));
                }  catch (CourseValidatorException e) {
                    logger.info(e.getMessage());
                    resp.sendError(400, e.getMessage());
                    return;
                }
                Integer createdId = courseService.create(name, userId, maxGrade);
                this.responseOut.print(String.format("{id: %d}", createdId));
            }
            else if (requestMapping("/join")) {
                Integer courseId;
                try {
                    courseId = courseValidator.getValidatedCourseId(req.getParameter("courseId"));
                }
                catch (CourseValidatorException e) {
                    logger.info(e.getMessage());
                    resp.sendError(400, e.getMessage());
                    return;
                }
                courseService.joinCourse(userId, courseId);
            }
            else if (requestMapping("/grade-student")) {
                String studentUserId = req.getParameter("studentUserId");
                Integer courseId;
                Integer grade;
                String teacherResponse = req.getParameter("teacherResponse");
                try {
                    userValidator.validateUserId(studentUserId);
                    courseId = courseValidator.getValidatedCourseId(req.getParameter("courseId"));
                    grade = courseGradeValidator.getValidatedGrade(req.getParameter("grade"));
                    courseGradeValidator.validateTeacherResponse(teacherResponse);
                }
                catch (CourseValidatorException | UserValidatorException | CourseGradeValidatorException e) {
                    logger.info(e.getMessage());
                    resp.sendError(400, e.getMessage());
                    return;
                }
                courseService.gradeStudent(courseId, studentUserId, grade, teacherResponse);
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
