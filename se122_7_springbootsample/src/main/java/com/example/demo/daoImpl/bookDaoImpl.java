package com.example.demo.daoImpl;
import com.example.demo.dao.BookDao;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import java.beans.Transient;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.getBookByBid(id);
    }

    @Override
    @Transactional
    public void deleteBook(Long id){
        bookRepository.deleteBookByBid(id);
    }

    @Override
    public void addBook(Map<String, String> book){
        Book newBook = new Book();
        newBook.setAuthor(book.get("author"));
        newBook.setTitle(book.get("title"));
        newBook.setType(book.get("type"));
        newBook.setPrice(book.get("price"));
        newBook.setInventory(book.get("inventory"));
        newBook.setDescription(book.get("description"));
        newBook.setIsbn(book.get("isbn"));
        newBook.setImage(book.get("image"));
        bookRepository.save(newBook);

//        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void updateInventory(Long bid, String quantity){
        Book book = bookRepository.getBookByBid(bid);
        book.setInventory(String.valueOf(Integer.parseInt(book.getInventory()) - Integer.parseInt(quantity)));
        bookRepository.save(book);
    }

    @Override
    public Book search(String keyword){
        return bookRepository.findBookByTitle(keyword);
    }

    @Override
    public void updateBook(Map<String, String> book, Long id){
        Book newBook = bookRepository.getBookByBid(id);
        newBook.setAuthor(book.get("author"));
        newBook.setTitle(book.get("title"));
        newBook.setType(book.get("type"));
        newBook.setPrice(book.get("price"));
        newBook.setInventory(book.get("inventory"));
        newBook.setDescription(book.get("description"));
        newBook.setIsbn(book.get("isbn"));
        newBook.setImage(book.get("image"));
        bookRepository.save(newBook);
    }

    @Override
    public Book getBook(Long id){
        return bookRepository.getBookByBid(id);
    }

    @Override
    public Book getBookByTitle(String title){
        return bookRepository.findBookByTitle(title);
    }
}
