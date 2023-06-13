package ua.lab1.web.filters;

import com.auth0.jwk.JwkException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.lab1.web.security.JwtValidator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.InvalidParameterException;

public class ValidateJWTAccessTokenFilter implements Filter {
    private Logger logger;
    private JwtValidator jwtValidator;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger = LogManager.getLogger(ValidateJWTAccessTokenFilter.class);
        logger.info("ValidateJWTAccessTokenFilter initialized");
        jwtValidator = new JwtValidator();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            logger.info("ValidateJWTAccessTokenFilter filtering request to " + httpRequest.getPathInfo());
            String authorizationHeader = httpRequest.getHeader("Authorization");
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                httpResponse.sendError(401, "Invalid Authorization header structure");
            } else {
                String token = authorizationHeader.substring("Bearer ".length());
                logger.info(token);
                try {
                    jwtValidator.validate(token);
                    logger.info("ValidateJWTAccessTokenFilter: token is valid");
                    chain.doFilter(httpRequest, httpResponse);
                }
                catch (Exception e) {
                    logger.error(e.getMessage());
                    System.out.println("Sending error");
                    httpResponse.sendError(401, e.getMessage());
                    System.out.println("End of catch");
                }
//                catch (JWTDecodeException e) {
//                    logger.error("Invalid access token structure");
//                    httpResponse.sendError(401, e.getMessage());
//                }
//                catch (InvalidParameterException e) {
//                    logger.error(e.getMessage());
//                    httpResponse.sendError(401, e.getMessage());
//                }
//                catch (JwkException | MalformedURLException e) {
//                    logger.error(e.getMessage());
//                    httpResponse.sendError(401, "Invalid access token");
//                }
            }
        }
        else {
            logger.warn("ValidateJWTAccessTokenFilter can filter only http request and response");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // no resources to free
    }
}
