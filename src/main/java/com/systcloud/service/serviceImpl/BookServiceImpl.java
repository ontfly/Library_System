package com.systcloud.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.systcloud.dao.BookMapper;
import com.systcloud.domain.Book;
import com.systcloud.domain.Record;
import com.systcloud.domain.User;
import com.systcloud.entity.pageResult;
import com.systcloud.service.BookService;
import com.systcloud.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BookServiceImpl implements BookService {
    @Autowired(required = false)
   public BookMapper bookMapper;
    @Autowired(required = false)
   private RecordService recordService;
    /**
     * pageNum当前页码
     * pageSize 每页显示数量*/
    //首页的图书列表
    @Override
    public pageResult selectNewBook(Integer pageNum, Integer pageSize) {
        //设置分页查询的参数，开始分页
        PageHelper.startPage(pageNum,pageSize);
       Page<Book> page = bookMapper.selectNewBook();
       return new pageResult(page.getTotal(),page.getResult());

    }

    @Override
    public Book findById(String id) {

        return bookMapper.findbyId(id);
    }
    @Override
    public Integer borrowBook(Book book){
        Book book1 = this.findById(book.getId()+"");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        book.setBorrowTime(dateFormat.format(new Date()));
        book.setPrice(book1.getPrice());
        book.setStatus("1");
        book.setUploadTime(book1.getUploadTime());
        return bookMapper.editBooks(book);
    }

    //图书借阅
    @Override
    public pageResult search(Book book, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Book> page = bookMapper.searchBooks(book);
        return new pageResult(page.getTotal(),page.getResult());
    }
    //新增图书
    @Override
    public Integer addBook(Book book) {
        return bookMapper.addBook(book);
    }
    //编辑图书
    @Override
    public Integer editBook(Book book) {
        return bookMapper.editBooks(book);
    }

    @Override
    public pageResult searchBorrowed(Book book, User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Book> page;
        if("ADMIN".equals(user.getRole())){
            page = bookMapper.selectBorrowed(book);
        }else{
            page = bookMapper.selectMyBorrowed(book);
        }
        return new pageResult(page.getTotal(),page.getResult());
    }
    //归还图书
    @Override
    public boolean returnBook(String id,User user) {
        Book book1 =this.findById(id);
       boolean rb = book1.getBorrower().equals(user.getName());
       if(rb){
           book1.setStatus("2");
           bookMapper.editBooks(book1);
       }
        return rb;
    }
    //确认归还图书并将图书相关借阅记录清零，将借阅记录写入数据库
    @Override
    @Transactional
    public Integer returnConfirm(String id) {
        Book book=this.findById(id);
        Record record = this.covertRecord(book);
        book.setStatus("0");
        book.setBorrower("");
        book.setReturnTime("");
        book.setBorrowTime("");
        Integer i = bookMapper.editBooks(book);
        if(i==1){
            return recordService.addRecord(record);
        }
        return 0;
    }

    //将Book类型的实例对象转换为Record类型的实例对象
    public Record covertRecord(Book book){
        Record record = new Record();
        record.setBookname(book.getName());
        record.setBookisbn(book.getIsbn());
        record.setBorrower(book.getBorrower());
        record.setBorrowTime(book.getBorrowTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        record.setRemandTime(dateFormat.format(new Date()));
        return record;

    }
    //根据id号删除图书
    @Override
    public Integer deleteBook(String id) {
        return bookMapper.deletebook(id);
    }
    //查询所有未借阅的图书



    //图书管理
    @Override
    public pageResult searchNoBorrowed(Integer pageNUm,Integer pageSize) {
        PageHelper.startPage(pageNUm,pageSize);
        Page<Book> page = bookMapper.reaserchbookks();
        return  new pageResult(page.getTotal(),page.getResult());
    }

}
