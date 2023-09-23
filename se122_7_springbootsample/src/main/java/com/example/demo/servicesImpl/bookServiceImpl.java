package com.example.demo.servicesImpl;
import com.example.demo.dao.bookDao;
import com.example.demo.services.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Book;
import com.example.demo.repository.bookRepository;

import java.util.List;
import java.util.Map;

@Service
public class bookServiceImpl implements bookService{
    @Autowired
    private bookDao bookDao;

    public bookServiceImpl(bookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    public Book getBookById(Long id) {
        return bookDao.getBookById(id);
    }

    public void deleteBook(Long id){
        bookDao.deleteBook(id);
    }

    public void addBook(Map<String, String> book){
        bookDao.addBook(book);
    }

    public Book search(String keyword){
        return bookDao.search(keyword);
    }

    public void updateBook(Map<String, String> book, Long id){
        bookDao.updateBook(book, id);
    }

    public Book getBook(Long id){
        return bookDao.getBook(id);
    }
}
