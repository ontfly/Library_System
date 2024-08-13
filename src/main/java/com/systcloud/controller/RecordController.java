package com.systcloud.controller;

import com.systcloud.domain.Record;
import com.systcloud.domain.User;
import com.systcloud.entity.pageResult;
import com.systcloud.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/record")
@Transactional

public class RecordController {
    @Autowired
    private RecordService recordService;
    @RequestMapping("/searchRecords")
    private ModelAndView searchRecords(Record record, HttpServletRequest request){
        Integer pageNum = null;Integer pageSize=null;
        User user  = (User) request.getSession().getAttribute("USER_SESSION");
        pageResult pageResult =recordService.searchRecord(record,user,pageNum,pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("records");
        modelAndView.addObject("pageResult",pageResult);
        modelAndView.addObject("search",record);
        modelAndView.addObject("pageNum",pageNum);
        modelAndView.addObject("gourl",request.getRequestURI());
        return  modelAndView;

    }
}
