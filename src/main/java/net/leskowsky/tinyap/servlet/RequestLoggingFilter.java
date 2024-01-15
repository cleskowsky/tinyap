package net.leskowsky.tinyap.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class RequestLoggingFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        var startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        logger.info(String.format("url: %s, queryParams: %s, method: %s, status: %d, duration_ms: %d",
                req.getRequestURI(),
                req.getQueryString(),
                req.getMethod(),
                resp.getStatus(),
                System.currentTimeMillis() - startTime
        ));
    }
}
