package com.systcloud.controller;

import org.springframework.stereotype.Controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "encodingFilter",urlPatterns = "/*")   //@WebFilter注解会在程序执行时自动把程序过滤器加载进入容器
public class EncodingFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }
    public void destroy() {
    }
}
