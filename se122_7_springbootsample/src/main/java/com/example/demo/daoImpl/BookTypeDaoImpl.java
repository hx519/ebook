package com.example.demo.daoImpl;

import com.example.demo.dao.BookTypeDao;
import com.example.demo.entity.BookType;
import com.example.demo.repository.BookTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookTypeDaoImpl implements BookTypeDao {
    @Autowired
    private BookTypeRepository bookTypeRepository;

    @Override
    public List<BookType> getAllBookTypes(){
        return bookTypeRepository.findAll();
    }
    @Override
    public List<BookType> getRelateBookTypes(String typeName){
        BookType type = bookTypeRepository.findBookTypeByTypeName(typeName);
        List<BookType> result = new ArrayList<>();
        List<BookType> list1 = bookTypeRepository.findRelatedBookTypes(typeName);
        List<BookType> list2 = bookTypeRepository.findRbtofRbt(typeName);
        result.add(type);
        for(BookType bookType: list1){
            if(!result.contains(bookType))
                result.add(bookType);
        }
        for(BookType bookType: list2){
            if(!result.contains(bookType))
                result.add(bookType);
        }
        return result;
    }
}
