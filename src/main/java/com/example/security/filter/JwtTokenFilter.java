package com.example.security.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class JwtTokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Before Filter");
        chain.doFilter(request, response);
    }
}
