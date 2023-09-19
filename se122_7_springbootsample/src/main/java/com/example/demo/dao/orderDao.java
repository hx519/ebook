package com.example.demo.dao;

import com.example.demo.entity.OrderItem;

import java.util.List;
import java.util.Map;
import com.example.demo.entity.MyOrder;

public interface orderDao {
    void addOrder(List<Map<String, Object>> order, String Uid);
    List<List<OrderItem>> getOrders(String Uid);
    MyOrder addMyOrder(String uid, String year, String month, String day, String hour, String minute, String total_price);
    void addOrderItem(String oid, String title, String author, String price, String quantity);
    List<MyOrder> getMyOrder(String Uid);
    List<OrderItem> getOrderItem(String oid);
    List<MyOrder> getAllMyOrder();
}
