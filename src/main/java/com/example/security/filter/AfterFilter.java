package com.example.security.filter;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class AfterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("after filter");
        chain.doFilter(request, response);
    }
}
