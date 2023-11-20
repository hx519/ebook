package com.example.demo.repository;

import com.example.demo.entity.MongoOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoOrderRepository extends MongoRepository<MongoOrder, String> {
    List<MongoOrder> findMongoOrdersByUid(String uid);
    MongoOrder findMongoOrderById(String id);
}
