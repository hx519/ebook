package com.example.demo.dao;

import com.example.demo.entity.MongoOrder;

import java.util.List;
import java.util.Map;

public interface MongoOrderDao {
    MongoOrder addOrder(List<Map<String, String>> orderItems, String uid, String price, String time);
    List<MongoOrder> getMyOrder(String uid);
    List<MongoOrder> getAllOrder();
}
