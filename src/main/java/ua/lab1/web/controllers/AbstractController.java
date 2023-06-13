package ua.lab1.web.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;


public abstract class AbstractController extends HttpServlet {

    protected static final Logger logger = LogManager.getLogger(AbstractController.class);
    protected HttpServletRequest req;
    protected HttpServletResponse resp;

    protected PrintWriter responseOut;

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.req = req;
        this.resp = resp;
        this.responseOut = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }

    protected boolean getMapping(String regex) {
        String pathInfo = req.getPathInfo();
        if (Pattern.matches(regex, pathInfo)) {
//            Pattern pattern = Pattern.compile(regex);
//            Matcher matcher = pattern.matcher(pathInfo);
//            while(matcher.find()) {
//                for (int i = 1; i <= matcher.groupCount(); i++) {
//                    pathParameters.add(matcher.group(i));
//                }
//            }
            return resp.getStatus() >= 200 && resp.getStatus() <= 299;
        }
        return false;
    }
}
