package com.example.demo.controllers;
import java.awt.*;
import java.util.ArrayList;

import com.example.demo.kafka.ProducerService;
import com.example.demo.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.BookService;
import com.example.demo.entity.Book;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    private  BookService bookService;

    @GetMapping("/bookList")
    public ResponseEntity<Msg> getAllBooks(){
        Msg result = bookService.getAllBooks();
        if(result.getStatus() >= 0){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @PostMapping("/deleteBook")
    public ResponseEntity<Msg> deleteBook(@RequestBody Long id){
        Msg result = bookService.deleteBook(id);
        if(result.getStatus() >= 0){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @PostMapping("/addBook")
    public ResponseEntity<Msg> addBook(@RequestBody Map<String, String> book){
        Msg result = bookService.addBook(book);
        if(result.getStatus() >= 0){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Msg> search(@RequestParam String keyword){
        Msg result = bookService.search(keyword);
        if(result.getStatus() >= 0){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @PostMapping("/updateBook/{id}")
    public ResponseEntity<Msg> updateBook(@RequestBody Map<String, String> book, @PathVariable String id){
        Msg result = bookService.updateBook(book, Long.parseLong(id));
        if(result.getStatus() >= 0){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @GetMapping("/getBook/{id}")
    public ResponseEntity<Msg> getBook(@PathVariable String id){
        Msg result = bookService.getBook(Long.parseLong(id));
        if(result.getStatus() >= 0){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

}

