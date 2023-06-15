package ua.lab1.web.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AccessFilter implements Filter {
    private Logger logger;

    private final List<String> teacherPaths = new ArrayList<>(List.of(new String[]{"/teacher-courses", "/create", "/grade-student", "/course-students"}));

    private final List<String> studentPaths = new ArrayList<>(List.of(new String[]{"/student-courses", "/student-grade", "/join"}));


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger = LogManager.getLogger(AccessFilter.class);
        logger.info("AccessFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            String authorizationHeader = httpRequest.getHeader("Authorization");
            String token = authorizationHeader.substring("Bearer ".length());
            try {
                final DecodedJWT jwt = JWT.decode(token);
                JSONArray jsonArray = new JSONArray(jwt.getClaim("realm_access").asMap().get("roles").toString());
                Iterator<Object> iterator = jsonArray.iterator();
                List<String> roles = new ArrayList<>();
                while(iterator.hasNext()) {
                    roles.add((String) iterator.next());
                }
                String pathInfo = httpRequest.getPathInfo();
                if ((!roles.contains("Teacher") && teacherPaths.contains(pathInfo))
                        || (!roles.contains("Student") && studentPaths.contains(pathInfo))) {
                    logger.info("Access denied, you don't have corresponding role");
                    httpResponse.sendError(401, "Access denied, you don't have corresponding role");
                }
                else {
                    chain.doFilter(httpRequest, httpResponse);
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                httpResponse.sendError(401, "Invalid access token");
            }
        }
        else {
            logger.warn("AccessFilter can filter only http request and response");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // no resources to free
    }
}
