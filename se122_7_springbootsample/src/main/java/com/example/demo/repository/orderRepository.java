package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<MyOrder,Integer>{
    List<MyOrder> findAllByUser(User user);
    MyOrder findByOid(Long oid);
}
