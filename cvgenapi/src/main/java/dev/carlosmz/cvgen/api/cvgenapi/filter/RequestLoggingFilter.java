package dev.carlosmz.cvgen.api.cvgenapi.filter;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(1)
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(req);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(res);
        try {
            chain.doFilter(requestWrapper, responseWrapper);
        } finally {
            if (req.getRequestURI().startsWith("/api/curriculums")) {
                String raw = new String(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding());
                System.out.println(">>> RAW JSON BODY: " + raw);
            }
            responseWrapper.copyBodyToResponse(); // MUY IMPORTANTE
        }
    }
    
}