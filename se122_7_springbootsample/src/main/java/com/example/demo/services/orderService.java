package com.example.demo.services;

import com.example.demo.entity.MyOrder;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.OrderItem;
import com.example.demo.utils.Msg;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OrderService {
    Msg getOrders(String Uid);
    void addOrder(List<Map<String, Object>> order, String Uid);
    Msg getAllOrders();
    Msg search(String keyword);
    Msg searchMy(String keyword, String Uid);
    Msg receiveOrder(Map<String, Object> data) throws JsonProcessingException;
}
