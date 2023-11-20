package com.example.demo.dao;
import com.example.demo.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookDao {
    List<Book> getBooks();
    Book getBookById(Long id);
    void deleteBook(Long id);
    void addBook(Map<String, String> book);
    boolean updateInventory(Long bid, String quantity);
    Book search(String keyword);
    void updateBook(Map<String, String> book, Long id);
    Book getBookByTitle(String title);
}
