package com.example.demo.daoImpl;
import com.example.demo.dao.bookDao;
import com.example.demo.entity.Book;
import com.example.demo.repository.bookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Map;

@Repository
public class bookDaoImpl implements bookDao{
    @Autowired
    private bookRepository bookRepository;

//    public bookDaoImpl(bookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.getBookByBid(id);
    }

    @Transactional
    public void deleteBook(Long id){
        bookRepository.deleteBookByBid(id);
    }

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

    public void updateInventory(Long bid, String quantity){
        Book book = bookRepository.getBookByBid(bid);
        book.setInventory(String.valueOf(Integer.parseInt(book.getInventory()) - Integer.parseInt(quantity)));
        bookRepository.save(book);
    }

    public Book search(String keyword){
        return bookRepository.findBookByTitle(keyword);
    }

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

    public Book getBook(Long id){
        return bookRepository.getBookByBid(id);
    }
}
