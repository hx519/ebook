package com.example.demo.controllers;
import java.awt.*;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.bookService;
import com.example.demo.entity.Book;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    private  bookService bookService;

    public BookController(bookService bookService) {
        this.bookService = bookService;
    }

    @CrossOrigin
    @GetMapping("/bookList")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @CrossOrigin
    @PostMapping("/deleteBook")
    public void deleteBook(@RequestBody String id){
        bookService.deleteBook(Long.parseLong(id));
    }

    @CrossOrigin
    @PostMapping("/addBook")
    public void addBook(@RequestBody Map<String, String> book){
        System.out.println(book);
        bookService.addBook(book);
    }

    @CrossOrigin
    @GetMapping("/search")
    public Book search(@RequestParam String title){
        return bookService.search(title);
    }

    @CrossOrigin
    @PostMapping("/updateBook/{id}")
    public void updateBook(@RequestBody Map<String, String> book, @PathVariable String id){
        bookService.updateBook(book, Long.parseLong(id));
    }

    @CrossOrigin
    @GetMapping("/getBook/{id}")
    public Book getBook(@PathVariable String id){
        return bookService.getBook(Long.parseLong(id));
    }

}

