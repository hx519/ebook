package com.example.demo.dao;

public interface OrderItemDao {
    void addOrderItem(String oid, String title, String author, String price, String quantity);
}
