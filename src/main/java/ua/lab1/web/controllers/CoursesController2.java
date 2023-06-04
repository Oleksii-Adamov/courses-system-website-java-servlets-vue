package ua.lab1.web.controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

//@WebServlet(value = "/test2")
public class CoursesController2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        //logger.info("ua.lab1.web.servlets.TestServlet doGet() called");
        try(PrintWriter pr = resp.getWriter()) {
            pr.println("<HTML>");
            pr.println("<BODY>");
            pr.println("API!");
            pr.println("</body>");
            pr.println("</html>");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
