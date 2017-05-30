package app.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class append HTTP headers for every request.
 * It is important for OPTIONS request for authentication
 */
public class OptionsFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}


    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "1");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, x-requested-with, remember-me");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
