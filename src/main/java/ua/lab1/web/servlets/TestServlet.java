package ua.lab1.web.servlets;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

//@WebServlet(value = "/test")
public class TestServlet extends HttpServlet {

    //protected static final Logger logger = LogManager.getLogger(ua.lab1.web.servlets.TestServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        //logger.info("ua.lab1.web.servlets.TestServlet doGet() called");
        try(PrintWriter pr = resp.getWriter()) {
            pr.println("<HTML>");
            pr.println("<BODY>");
            pr.println("HELLO!");
            pr.println("</body>");
            pr.println("</html>");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
