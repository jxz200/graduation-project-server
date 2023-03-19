//package com.example.windserver.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.IOException;
//
//@WebFilter(filterName = "loginVerificationFilter", urlPatterns = "/*")
//@Slf4j
//public class LoginVerificationFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
//        String requestPath = httpRequest.getRequestURI();
//        log.info("拦截到请求 {}", requestPath);
//        HttpSession session = httpRequest.getSession(false);
//        filterChain.doFilter(httpRequest, httpResponse);
//    }
//}
