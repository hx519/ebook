package com.example.demo.controllers;

import com.example.demo.dao.BookDao;
import com.example.demo.entity.Book;
import com.example.demo.services.BookService;
import com.example.demo.utils.Msg;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookResolver implements GraphQLQueryResolver {
    @Autowired
    private BookService bookService;

    public Book getBookByGraphql(String title){
        Book result = bookService.getBookByTitle(title);
        return result;
    }
}
