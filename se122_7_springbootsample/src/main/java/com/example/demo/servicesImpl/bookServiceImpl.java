package com.example.demo.servicesImpl;
import com.example.demo.dao.BookDao;
import com.example.demo.services.BookService;
import com.example.demo.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Book;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    public Msg getAllBooks(){
        List<Book> books = bookDao.getBooks();
        return new Msg(1, "get all books success", books);
    }

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

    public Msg search(String keyword){
        Book book = bookDao.search(keyword);
        if(book == null)
            return new Msg(-1, "book not exist");
        else
            return new Msg(1, "search book success", book);
    }

    public Msg updateBook(Map<String, String> book, Long id){
        Book book1 = bookDao.getBookById(id);
        if(book1 == null)
            return new Msg(-1, "book not exist");
        bookDao.updateBook(book, id);
        book1 = bookDao.getBookById(id);
        return new Msg(1, "update book success");
    }

    public Msg getBook(Long id){
        Book book = bookDao.getBookById(id);
        if(book == null)
            return new Msg(-1, "book not exist");
        else
            return new Msg(1, "get book success", book);
    }
}
