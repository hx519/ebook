package com.example.demo.dao;

import com.example.demo.entity.BookType;

import java.util.List;

public interface BookTypeDao {
    List<BookType> getAllBookTypes();
    List<BookType> getRelateBookTypes(String typeName);
}
