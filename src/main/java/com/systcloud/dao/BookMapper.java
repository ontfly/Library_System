package com.systcloud.dao;

import com.github.pagehelper.Page;
import com.systcloud.domain.Book;


public interface BookMapper {
    Page<Book> selectNewBook();
    Book findbyId(String  id);
    Integer editBooks(Book book);
    Page<Book> searchBooks(Book book);

    Integer addBook(Book book);

    Page<Book> selectMyBorrowed(Book book);

    Page<Book> selectBorrowed(Book book);
    Integer deletebook(String id);
    Page<Book> reaserchbookks();

}
