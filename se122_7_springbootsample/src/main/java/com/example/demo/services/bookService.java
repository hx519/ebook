package com.example.demo.services;
import com.example.demo.repository.BookRepository;
import com.example.demo.entity.Book;
import com.example.demo.utils.Msg;

import java.util.List;
import java.util.Map;

public interface BookService {
    Msg getAllBooks();
    Msg deleteBook(Long id);
    Msg addBook(Map<String, String> book);
    Msg search(String keyword);
    Msg updateBook(Map<String, String> book, Long id);
    Msg getBook(Long id);
    Msg getBookTypeNames();
    Msg getBookByType(String typeName);
    Book getBookByTitle(String title);
    Msg createBookDescriptionTxt();
    boolean addBookDescriptionTxt(String path, String description);
    boolean deleteAllFilesInDirectory(String directoryPath);
    Msg getWordCount();
    Msg getWordCountByKeyword(String keyword);
}
