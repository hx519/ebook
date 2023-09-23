package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface bookRepository extends JpaRepository<Book,Integer>{
    List<Book> findAll();
    Book getBookByBid(Long bid);
    void deleteBookByBid(Long bid);
    Book findBookByTitle(String title);
}