package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractController extends HttpServlet {

    protected HttpServletRequest req;
    protected HttpServletResponse resp;

    protected PrintWriter out;

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.req = req;
        this.resp = resp;
        this.out = resp.getWriter();
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
