package dev.carlosmz.cvgen.api.cvgenapi.filter;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
@Order(1)
public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(request);
        chain.doFilter(wrapper, res);
        byte[] buf = wrapper.getContentAsByteArray();
        if (buf.length > 0 && request.getRequestURI().startsWith("/api/curriculums")) {
            String raw = new String(buf, wrapper.getCharacterEncoding());
            System.out.println(">>> RAW JSON BODY: " + raw);
            System.out.println(">>> CONTENT-TYPE: " + request.getContentType());
        }
    }

}