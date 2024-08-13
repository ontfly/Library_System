package com.systcloud.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
//自定义异常处理器
@Component
public class ExceptionController implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Writer out = new StringWriter();
        PrintWriter print = new PrintWriter(out);
        System.out.println(print);
        ex.printStackTrace(print);
        String sysMsg = out.toString();
        System.out.println(sysMsg);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","Internet error");
        modelAndView.setViewName("error");
        return  modelAndView;
    }
}
