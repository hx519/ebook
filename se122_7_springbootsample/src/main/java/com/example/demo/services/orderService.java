package com.example.demo.services;

import com.example.demo.entity.MyOrder;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.OrderItem;

public interface orderService {
//    List<List<order_item>> getOrders(String Uid);
    List<Map<String, Object>> getOrders(String Uid);
    void addOrder(List<Map<String, Object>> order, String Uid);
    List<Map<String, Object>> getAllOrders();
    List<Map<String, Object>> search(String keyword);
    List<Map<String, Object>> searchMy(String keyword, String Uid);
}
