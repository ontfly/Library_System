package com.systcloud.controller;

import com.systcloud.domain.Book;
import com.systcloud.domain.User;
import com.systcloud.entity.Result;
import com.systcloud.entity.pageResult;
import com.systcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Transactional
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(User user, HttpServletRequest httpServletRequest) {
        System.out.println(user);
        try {
            User u = userService.login(user);
            if (u != null) {
                httpServletRequest.getSession().setAttribute("USER_SESSION",u);
                u.setStatus("1");
                userService.logindd(u);
                return "redirect:/web/main.jsp";
            } else {
                httpServletRequest.setAttribute("msg","密码或用户名错误");
                return "forward:/web/login.jsp";
            }

        }
    catch(Exception e){
        e.printStackTrace();
        httpServletRequest.setAttribute("msg","系统错误");
        return "forward:/web/login.jsp";
    }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        try {
            User u  = (User) request.getSession().getAttribute("USER_SESSION");
            u.setStatus("0");
            userService.logout(u);
            HttpSession session = request.getSession();
            session.invalidate();
            return "forward:/web/login.jsp";
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("msg","system error");
            return "forward:/web/login.jsp";
        }
    }
    @RequestMapping("/usermanage")
    public ModelAndView usermanage(Integer pageNum,Integer pageSize,HttpServletRequest request){
        if(pageNum ==null){
            pageNum=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        ModelAndView modelAndView = new ModelAndView();
        pageResult pageResult = userService.usermanage(pageNum,pageSize);
        modelAndView.setViewName("usermanage");
        modelAndView.addObject("pageResult",pageResult);
        modelAndView.addObject("pageNum",pageNum);
        modelAndView.addObject("gourl",request.getRequestURI());
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping("/deleteuser")
    public Result deleteaccount(User user,HttpServletRequest request){
        User usr = (User) request.getSession().getAttribute("USER_SESSION");
        System.out.println(usr);
        System.out.println(user);
        if(usr.getRole().equals("ADMIN")&&(usr.getId() == null ? user.getId() != null : !usr.getId().equals(user.getId()))){
            userService.deleteuseraccount(user);
            System.out.println("success");
            return new Result(true,"done");
        }
        else {
            return new Result(false,"deny");
        }
    }
    @ResponseBody
    @RequestMapping("/addUser")
    public Result adduser(User user,HttpServletRequest request){
        User usr = (User) request.getSession().getAttribute("USER_SESSION");
        if(usr.getRole().equals("ADMIN")){
            Integer i = userService.adduser(user);
            try {
                if(i!=null){
                    return new Result(true,"Add success");
                }else {
                    return  new Result(false,"add failed");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return  new Result(false,"add failed");

    }
    @ResponseBody
    @RequestMapping("/findaccount")
    public Result finduserById(String id){
        User user = userService.findaccountt(id);
        System.out.println(user+"findaccount");
        try {
            if (user != null) {
                return new Result<User>(true, "query success", user);
            } else {
                return new Result<User>(false, "query failed");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result<User>(false,"System error，query failed");
        }

    }
    @ResponseBody
    @RequestMapping("/edituser")
    public Result edituser(User user){
        System.out.println(user+"edituser");
        Integer i =userService.editUser(user);
        try {
            if(i!=1){
                return new Result(false,"update account failed");
            }else {
                return new Result(true,"update success");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}