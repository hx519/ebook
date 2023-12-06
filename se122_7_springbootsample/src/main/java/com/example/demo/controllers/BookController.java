package com.example.demo.controllers;
import java.awt.*;
import java.util.ArrayList;

import com.example.demo.entity.BookType;
import com.example.demo.kafka.ProducerService;
import com.example.demo.redis.RedisUtil;
import com.example.demo.repository.BookTypeRepository;
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
    @Autowired
    private BookTypeRepository bookTypeRepository;
    @Autowired
    private RedisUtil redisUtil;

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

    @GetMapping("/getBookTypeNames")
    public ResponseEntity<Msg> getBookTypeNames(){
        Msg result = bookService.getBookTypeNames();
        if(result.getStatus() >= 0){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @GetMapping("/getBookByType/{typename}")
    public ResponseEntity<Msg> getBookByType(@PathVariable String typename){
        Msg result = bookService.getBookByType(typename);
        if(result.getStatus() >= 0){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @RequestMapping("/initRedis")
    public ResponseEntity<Msg> initRedis(){
        redisUtil.del("book*");
        return ResponseEntity.ok(new Msg(1, "redis init success"));
    }

    @RequestMapping("/neo4j")
    public ResponseEntity<Msg> initNeo4j(){
        // 先删除所有的内容
        bookTypeRepository.deleteAll();
        // 添加书籍类型
        BookType bookType1 = new BookType("奇幻");
        BookType bookType2 = new BookType("儿童奇幻");
        BookType bookType3 = new BookType("神话奇幻");
        BookType bookType4 = new BookType("经典奇幻");
        BookType bookType5 = new BookType("小说");
        BookType bookType6 = new BookType("推理小说");
        BookType bookType7 = new BookType("科普");
        BookType bookType8 = new BookType("历史");
        BookType bookType9 = new BookType("文学");
        BookType bookType10 = new BookType("科幻小说");
        BookType bookType11 = new BookType("古典小说");
        BookType bookType12 = new BookType("传记");
        BookType bookType13 = new BookType("心理学");
        BookType bookType14 = new BookType("戏剧");
        BookType bookType15 = new BookType("计算机科学");

        // 数据准备
        bookType1.addBookID(Long.parseLong("1"));
        bookType1.addBookID(Long.parseLong("2"));
        bookType1.addBookID(Long.parseLong("3"));
        bookType2.addBookID(Long.parseLong("4"));
        bookType3.addBookID(Long.parseLong("5"));
        bookType4.addBookID(Long.parseLong("6"));
        bookType4.addBookID(Long.parseLong("7"));
        bookType4.addBookID(Long.parseLong("8"));
        bookType5.addBookID(Long.parseLong("9"));
        bookType5.addBookID(Long.parseLong("10"));
        bookType6.addBookID(Long.parseLong("11"));
        bookType5.addBookID(Long.parseLong("12"));
        bookType5.addBookID(Long.parseLong("13"));
        bookType7.addBookID(Long.parseLong("14"));
        bookType8.addBookID(Long.parseLong("15"));
        bookType10.addBookID(Long.parseLong("16"));
        bookType11.addBookID(Long.parseLong("17"));
        bookType9.addBookID(Long.parseLong("18"));
        bookType12.addBookID(Long.parseLong("19"));
        bookType1.addBookID(Long.parseLong("20"));
        bookType13.addBookID(Long.parseLong("21"));
        bookType14.addBookID(Long.parseLong("22"));
        bookType15.addBookID(Long.parseLong("23"));

        // 奇幻
        bookType1.addRelateBookType(bookType2);
        bookType1.addRelateBookType(bookType3);
        bookType1.addRelateBookType(bookType4);
        // 小说
        bookType5.addRelateBookType(bookType6);
        bookType5.addRelateBookType(bookType10);
        bookType5.addRelateBookType(bookType11);
        // 文学
        bookType9.addRelateBookType(bookType8);
        bookType9.addRelateBookType(bookType12);
        bookType9.addRelateBookType(bookType13);
        bookType9.addRelateBookType(bookType14);
        bookType9.addRelateBookType(bookType5);
        // 推理小说
        bookType6.addRelateBookType(bookType7);
        bookType6.addRelateBookType(bookType1);
        bookType6.addRelateBookType(bookType15);

//        bookType3.addRelateBookType(bookType1);
//        bookType4.addRelateBookType(bookType1);
//        bookType2.addRelateBookType(bookType1);
//        bookType10.addRelateBookType(bookType5);
//        bookType11.addRelateBookType(bookType5);
//        bookType6.addRelateBookType(bookType5);
//        bookType12.addRelateBookType(bookType9);
//        bookType13.addRelateBookType(bookType9);
//        bookType14.addRelateBookType(bookType9);
//        bookType8.addRelateBookType(bookType9);


        bookTypeRepository.save(bookType1);
        bookTypeRepository.save(bookType2);
        bookTypeRepository.save(bookType3);
        bookTypeRepository.save(bookType4);
        bookTypeRepository.save(bookType5);
        bookTypeRepository.save(bookType6);
        bookTypeRepository.save(bookType7);
        bookTypeRepository.save(bookType8);
        bookTypeRepository.save(bookType9);
        bookTypeRepository.save(bookType10);
        bookTypeRepository.save(bookType11);
        bookTypeRepository.save(bookType12);
        bookTypeRepository.save(bookType13);
        bookTypeRepository.save(bookType14);
        bookTypeRepository.save(bookType15);

        return ResponseEntity.ok(new Msg(1, "neo4j init success"));
    }


}

