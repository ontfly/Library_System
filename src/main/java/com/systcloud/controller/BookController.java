package com.systcloud.controller;

import com.systcloud.domain.Book;
import com.systcloud.domain.User;
import com.systcloud.entity.Result;
import com.systcloud.entity.pageResult;
import com.systcloud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/book")
@Transactional
public class BookController {
    @Autowired(required = false)
    private BookService bookService;
    @RequestMapping("/bookUpload")
    public String bookupload(HttpServletRequest request){
            return "booksload";

    }
    //图书管理

    @RequestMapping("/searchNoborrowBook")
    public  ModelAndView searchbookno(HttpServletRequest request,Integer pageNum,Integer pageSize){
        if(pageNum ==null){
            pageNum=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        pageResult pageResult = bookService.searchNoBorrowed(pageNum,pageSize);
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("bookmanage");
        modelAndView.addObject("pageResult", pageResult);
        modelAndView.addObject("pageNum",pageNum);
        modelAndView.addObject("gourl",request.getRequestURI());
        return modelAndView;

    }
    @ResponseBody
    @RequestMapping("/deletebook")
    public Result deletebook(String id,HttpServletRequest request){
        User usr = (User) request.getSession().getAttribute("USER_SESSION");
        if(usr.getRole().equals("ADMIN")){
            Integer i = bookService.deleteBook(id);
            try
            {
                if (i == 1) {
                    return new Result(true, "delete success");
                } else return new Result(false, "delete failure");
            }catch (Exception e){
                e.printStackTrace();
                return  new Result(false,"delete failed");
            }
        }else
            return  new Result(false,"delete failed");

    }
//首页的图书列表
    @RequestMapping("/selectNewbooks")
    public ModelAndView searchBook(HttpServletRequest request,Integer pageNum,Integer pageSize){
        if(pageNum ==null){
            pageNum=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
       pageResult result = bookService.selectNewBook(pageNum,pageSize);
        ModelAndView model = new ModelAndView();
        model.setViewName("newbooks");
        model.addObject("pageNum",pageNum);
        model.addObject("pageResult",result);
        model.addObject("gourl",request.getRequestURI());
        return model;
    }
    //查询图书信息
    @ResponseBody
    @RequestMapping("/findById")
    public Result<Book> findbookById(String id){
        Book book = bookService.findById(id);
       try {
            if (book != null) {
                return new Result<Book>(true, "query success", book);
            } else {
                return new Result<Book>(false, "query failed");
            }
        }catch (Exception e){
           e.printStackTrace();
           return new Result<Book>(false,"System error，query failed");
       }
}


//图书借阅
    @ResponseBody
    @RequestMapping("/search")
    public ModelAndView search(Book book,Integer pageNum,Integer pageSize,HttpServletRequest request){
        if(pageNum ==null){
            pageNum=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        System.out.println(book+"....");
        pageResult pageResult = bookService.search(book,pageNum,pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("books");
        modelAndView.addObject("pageResult",pageResult);
        //将查询条件返回到页面中
        modelAndView.addObject("search",book);
        //将新的一页的页码发返回到页面中
        modelAndView.addObject("pageNum",pageNum);
        //将当前查询的控制器路径返回到页面，页面变化时继续向该路径发送请求
        modelAndView.addObject("gourl",request.getRequestURI());
        return modelAndView;

    }
    @ResponseBody
    @RequestMapping("/addBook")
    public Result addBook(Book book,HttpServletRequest request){
        User usr = (User) request.getSession().getAttribute("USER_SESSION");
        if(usr.getRole().equals("ADMIN")){
            try{
                Integer i = bookService.addBook(book);
                if (i != 1){
                    return new Result(false,"inserted failed");
                }
                return  new Result(true,"inserted success");
            }catch (Exception e){
                e.printStackTrace();
                return new Result(false ,"insert failed");
            }
        }else
            return new Result(false ,"insert failed");

    }
    @ResponseBody
    @RequestMapping("/editBook")
    public Result editBook(Book book,HttpServletRequest request){
        User usr = (User) request.getSession().getAttribute("USER_SESSION");
        if(usr.getRole().equals("ADMIN")){
            try{
                Integer i =bookService.editBook(book);
                if(i!=1) {
                    return new Result(false,"edit failed");
                }
                return new Result(true,"edited success");
            }catch (Exception e){
                e.printStackTrace();
                return new Result(false,"edited failed");
            }
        }
       else
            return new Result(false,"edited failed");


    }
    @ResponseBody
    @RequestMapping("/borrowBook")
    public Result borrowbook(Book book,HttpSession session){
        String pname=((User)session.getAttribute("USER_SESSION")).getName();
        book.setBorrower(pname);
        try{
            Integer i  = bookService.borrowBook(book);
            if(i != 1){
                return new Result(false,"borrow failed");
            }
            return  new Result(true,"borrow success");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"borrowed failed");
        }
    }
    //当前借阅与图书归还
    @RequestMapping("/searchBorrowed")
    public  ModelAndView searchBorrowed(Book book,Integer pageNum,Integer pageSize,HttpServletRequest request){
        if(pageNum == null){
            pageNum=1;
        }
        if(pageSize == null){
            pageSize=10;
        }
        User user = (User)request.getSession().getAttribute("USER_SESSION");
        pageResult pageResult = bookService.searchBorrowed(book,user,pageNum,pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("borrowed");
        modelAndView.addObject("pageResult",pageResult);
        modelAndView.addObject("search",book);
        modelAndView.addObject("pageNum",pageNum);
        modelAndView.addObject("gourl",request.getRequestURI());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/returnBook")

    public Result returnBook(String id,HttpSession session){
        User user= (User)session.getAttribute("USER_SESSION");
        try
        {
            boolean flag = bookService.returnBook(id, user);
            if (flag) {
                return new Result(true, "returning confirmation,please return the " +
                        "book to the administrative center first");
            }
            return new Result(false, "returning failed");
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(false,"returning failed");
        }
    }

    @ResponseBody
    @Transactional
    @RequestMapping("/returnConfirm")
    public Result returnConfirm(String id){
        try{
           Integer i =  bookService.returnConfirm(id);
            System.out.println(i);
           if(i != 1){
               throw new Exception();
           }
           return  new Result(true,"confirm success");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"confirm failed");
        }

    }


}

