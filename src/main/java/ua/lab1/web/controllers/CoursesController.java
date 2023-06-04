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

//@WebServlet(value = "/api")
public class CoursesController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
//        try(PrintWriter pr = resp.getWriter()) {
//            pr.println("<HTML>");
//            pr.println("<BODY>");
//            pr.println("HELLO!");
//            pr.println("</body>");
//            pr.println("</html>");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        logger.info("CoursesController doGet() called");
//        System.out.println("got request");
        try {
            processRequest(req, resp);
            if (getMapping("/student-courses")) {
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
