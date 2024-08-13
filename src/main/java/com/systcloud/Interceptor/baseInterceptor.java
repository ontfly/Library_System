package com.systcloud.Interceptor;

import com.systcloud.domain.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class baseInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        String url = request.getRequestURI();
        if (user != null) {
            return true;
        }
        if (url.indexOf("login") >= 0) {
            return true;
        }
        request.setAttribute("msg", "please login your account");
        request.getRequestDispatcher("/web/login.jsp").forward(request, response);
        return false;
    }
}
