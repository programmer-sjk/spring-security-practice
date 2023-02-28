package com.example.security.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class BasicAuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("after filter");
        chain.doFilter(request, response);
    }
}
