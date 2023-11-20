package com.example.demo.daoImpl;
import com.alibaba.fastjson.JSON;
import com.example.demo.dao.BookDao;
import com.example.demo.entity.Book;
import com.example.demo.redis.RedisUtil;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;
import java.util.Map;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor=Exception.class)
    public Book getBookById(Long id) {
        Book book;
        Object redisGet = redisUtil.get("book" + id);
        if(redisGet == null){
            book = bookRepository.getBookByBid(id);
            if(book == null)
                return null;
            redisUtil.set("book" + id, JSON.toJSONString(book));
            System.out.println("get book" + id + "from mysql");
            System.out.println("redis set book" + id);
        }
        else{
            String json = redisGet.toString();
            if (json != null && !json.isEmpty()) {
                book = JSON.parseObject(json, Book.class);
                System.out.println("get book" + id + "from redis");
            } else {
                // 处理Redis中存储的数据无效或为空的情况。
                book = bookRepository.getBookByBid(id);
                if (book == null)
                    return null;
                redisUtil.set("book" + id, JSON.toJSONString(book));
                System.out.println("get book" + id + "from mysql");
                System.out.println("redis set book" + id);
            }
        }
        return book;
    }

    @Override
    @Transactional
    public void deleteBook(Long id){
        redisUtil.del("book" + id);
        bookRepository.deleteBookByBid(id);
        System.out.println("redis delete book" + id);
    }

    @Override
    @Transactional
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

        Object redisGet = redisUtil.get("book" + newBook.getBid());
        if(redisGet != null){
            redisUtil.del("book" + newBook.getBid());
            System.out.println("redis delete book" + newBook.getBid());
        }
//        redisUtil.set("book" + newBook.getBid(), JSON.toJSONString(newBook));
//        System.out.println("redis set book" + newBook.getBid());
    }

    @Override
    @Transactional
    public boolean updateInventory(Long bid, String quantity){
        Book book = bookRepository.getBookByBid(bid);
        if(Integer.parseInt(book.getInventory()) < Integer.parseInt(quantity))
            return false;
        book.setInventory(String.valueOf(Integer.parseInt(book.getInventory()) - Integer.parseInt(quantity)));
        bookRepository.save(book);
        Object redisGet = redisUtil.get("book" + bid);
        if(redisGet != null) {
            redisUtil.set("book" + bid, JSON.toJSONString(book));
            System.out.println("redis set book" + bid);
        }
        return true;
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
        Object redisGet = redisUtil.get("book" + newBook.getBid());
        if(redisGet != null) {
            redisUtil.set("book" + newBook.getBid(), JSON.toJSONString(newBook));
            System.out.println("redis set book" + newBook.getBid());
        }
    }

    @Override
    public Book getBookByTitle(String title){
        return bookRepository.findBookByTitle(title);
    }
}
