package com.example.authorservice.contrllers;

import com.example.authorservice.entity.Book;
import com.example.authorservice.repository.BookRepository;
import com.example.authorservice.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/getAuthor/{title}")
    public ResponseEntity<Msg> getAuthor(@PathVariable String title){
        Book book = bookRepository.findBookByTitle(title);
        Msg result = new Msg();
        if(book == null){
            result.setStatus(-1);
            result.setMessage("Book not found");
        }
        else{
            result.setStatus(1);
            result.setMessage("Success");
            result.setData(book.getAuthor());
        }
        if(result.getStatus() >= 0){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }
}
