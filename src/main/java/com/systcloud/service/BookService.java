package com.systcloud.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;
import com.systcloud.domain.Book;
import com.systcloud.domain.Record;
import com.systcloud.domain.User;
import com.systcloud.entity.pageResult;
import org.springframework.transaction.annotation.Transactional;


public interface BookService {
    pageResult selectNewBook(Integer pageNum,Integer pageSize);
    Book findById(String id);

    //借阅图书
    Integer borrowBook(Book book);

    pageResult search(Book book,Integer pageNum,Integer pageSize);
    Integer addBook(Book book);
    Integer editBook(Book book);

    pageResult searchBorrowed(Book book, User user,Integer pageNum,Integer pageSize);

    boolean returnBook(String id,User user);

    Integer returnConfirm(String id);
    Integer deleteBook(String id);

    pageResult searchNoBorrowed(Integer pageNum,Integer pageSize);



}
