package com.example.demo.dao;
import com.example.demo.entity.Book;

import java.util.List;
import java.util.Map;

public interface bookDao {
    List<Book> getBooks();
    Book getBookById(Long id);
    void deleteBook(Long id);
    void addBook(Map<String, String> book);
    void updateInventory(Long bid, String quantity);
    Book search(String keyword);
    void updateBook(Map<String, String> book, Long id);
    Book getBook(Long id);
}
