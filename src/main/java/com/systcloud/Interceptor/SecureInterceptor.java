package com.systcloud.Interceptor;

import com.systcloud.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@PropertySource("classpath:secureUrl.properties")
public class SecureInterceptor extends HandlerInterceptorAdapter {
    @Value("#{'${ignoreUrl}'.split(',')}")
    private List<String> ignoreUrl;
    public SecureInterceptor(List<String> ignoreUrl){
        this.ignoreUrl=ignoreUrl;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求路径
        String url = request.getRequestURI();
        if(url.indexOf("/login") >=0){
            return true;
        }
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        if(user != null){
            if("ADMIN".equals(user.getRole())){
                return true;
            }
            else if (!"ADMIN".equals(user.getRole())){
                for(String urll:ignoreUrl){
                    if (url.indexOf(urll) >= 0){

                        return  true;
                    }
                }
            }
        }
        request.setAttribute("msg", "please login first --__--");
        request.getRequestDispatcher("/web/login.jsp").forward(request,response);
        return false;
    }
}
