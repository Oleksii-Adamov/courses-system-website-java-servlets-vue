package ua.lab1.web.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserIdentificationFilter implements Filter {

    private Logger logger;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger = LogManager.getLogger(UserIdentificationFilter.class);
        logger.info("UserIdentificationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String authorizationHeader = httpRequest.getHeader("Authorization");
        String token = authorizationHeader.substring("Bearer ".length());
        final DecodedJWT jwt = JWT.decode(token);
        logger.info(jwt.getHeader());
        logger.info(jwt.getPayload());
    }

    @Override
    public void destroy() {
        // no resources to free
    }
}
