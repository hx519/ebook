package com.example.demo.servicesImpl;
import com.example.demo.dao.BookDao;
import com.example.demo.dao.BookTypeDao;
import com.example.demo.entity.BookType;
import com.example.demo.repository.BookTypeRepository;
import com.example.demo.services.BookService;
import com.example.demo.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private BookTypeDao bookTypeDao;

    @Override
    public Msg getAllBooks(){
        List<Book> books = bookDao.getBooks();
        List<Map<String, Object>> result = new ArrayList<>();
        for(Book book: books){
            Map<String, Object> map = new HashMap<>();
            map.put("bid", book.getBid());
            map.put("title", book.getTitle());
            map.put("price", book.getPrice());
            map.put("image", book.getImage());
            map.put("type", book.getType());
            result.add(map);
        }
        return new Msg(1, "get all books success", result);
    }

    @Override
    public Msg deleteBook(Long id){
        Book book = bookDao.getBookById(id);
        if(book == null)
            return new Msg(-1, "book not exist");
        bookDao.deleteBook(id);
        book = bookDao.getBookById(id);
        if(book == null)
            return new Msg(1, "delete book success");
        else
            return new Msg(-1, "delete book failed");
    }

    @Override
    public Msg addBook(Map<String, String> book){
        Book book1 = bookDao.getBookByTitle(book.get("title"));
        if(book1 != null)
            return new Msg(-1, "book already exist");
        bookDao.addBook(book);
        book1 = bookDao.getBookByTitle(book.get("title"));
        if(book1 != null)
            return new Msg(1, "add book success");
        else
            return new Msg(-1, "add book failed");
    }

    @Override
    public Msg search(String keyword){
        Book book = bookDao.search(keyword);
        if(book == null)
            return new Msg(-1, "book not exist");
        else
            return new Msg(1, "search book success", book);
    }

    @Override
    public Msg updateBook(Map<String, String> book, Long id){
        Book book1 = bookDao.getBookById(id);
        if(book1 == null)
            return new Msg(-1, "book not exist");
        bookDao.updateBook(book, id);
        book1 = bookDao.getBookById(id);
        return new Msg(1, "update book success");
    }

    @Override
    public Msg getBook(Long id){
        Book book = bookDao.getBookById(id);
        if(book == null)
            return new Msg(-1, "book not exist");
        else
            return new Msg(1, "get book success", book);
    }

    @Override
    public Msg getBookTypeNames(){
        List<BookType> bookTypes = bookTypeDao.getAllBookTypes();
        List<String> result = new ArrayList<>();
        for(BookType bookType: bookTypes){
            result.add(bookType.getTypeName());
        }
        return new Msg(1, "get book type success", result);
    }

    @Override
    public Msg getBookByType(String typeName){
        // 直接找到两层之内所有类型
        List<BookType> bookTypes = bookTypeDao.getRelateBookTypes(typeName);
        List<Book> books = new ArrayList<>();
        for(BookType bookType: bookTypes){
            List<Long> bookIDs = bookType.getBookIDs();
            for(Long bookID: bookIDs){
                Book book = bookDao.getBookById(bookID);
                books.add(book);
            }
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for(Book book: books){
            Map<String, Object> map = new HashMap<>();
            map.put("bid", book.getBid());
            map.put("title", book.getTitle());
            map.put("price", book.getPrice());
            map.put("image", book.getImage());
            map.put("type", book.getType());
            result.add(map);
        }
        return new Msg(1, "get book by type success", result);
    }
}
