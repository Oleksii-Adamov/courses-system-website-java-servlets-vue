package ua.lab1.web.controllers;

import com.google.gson.Gson;
import ua.lab1.web.enitities.Course;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CoursesController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            processRequest(req, resp);
            if (getMapping("/student-courses")) {
                List<Course> courses = new ArrayList<>();
                courses.add(new Course(1, "Test course1"));
                courses.add(new Course(2, "Test course2"));
                String coursesJsonString = new Gson().toJson(courses);
                this.out.print(coursesJsonString);
                logger.info("response from /student-courses :" + coursesJsonString);
            }
            if (getMapping("/teacher-courses")) {
                List<Course> courses = new ArrayList<>();
                courses.add(new Course(3, "Teacher course3"));
                courses.add(new Course(4, "Teacher course4"));
                String coursesJsonString = new Gson().toJson(courses);
                this.out.print(coursesJsonString);
                logger.info("response from /teacher-courses :" + coursesJsonString);
            }

        } catch (Exception e) {
            //System.out.println(e.getMessage());
            logger.error(e.getMessage());
        }
        this.out.flush();
    }
}
