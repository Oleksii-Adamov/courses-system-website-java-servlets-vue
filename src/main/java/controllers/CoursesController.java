package controllers;

import com.google.gson.Gson;
import ua.lab1.web.enitities.Course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class CoursesController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            processRequest(req, resp);
            if (getMapping("/api/student-courses")) {
                List<Course> courses = new ArrayList<>();
                courses.add(new Course(1, "Test course1"));
                courses.add(new Course(2, "Test course2"));
                String coursesJsonString = new Gson().toJson(courses);
                System.out.println(coursesJsonString);
                out.print(coursesJsonString);
                out.flush();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
