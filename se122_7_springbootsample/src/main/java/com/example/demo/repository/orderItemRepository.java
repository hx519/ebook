package com.example.demo.repository;
import java.util.List;

import com.example.demo.entity.MyOrder;
import com.example.demo.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface orderItemRepository extends JpaRepository<OrderItem,Integer>{

    List<OrderItem> findAllByOrder(MyOrder order);
}
