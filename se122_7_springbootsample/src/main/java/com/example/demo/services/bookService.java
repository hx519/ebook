package com.example.demo.services;
import com.example.demo.repository.bookRepository;
import com.example.demo.entity.Book;

import java.util.List;
import java.util.Map;

public interface bookService {
    List<Book> getBooks();
    Book getBookById(Long id);
    void deleteBook(Long id);
    void addBook(Map<String, String> book);
    Book search(String keyword);
    void updateBook(Map<String, String> book, Long id);
    Book getBook(Long id);
}
