package com.example.demo.controllers;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.demo.entity.OrderItem;
import com.example.demo.services.orderService;

@RestController
public class OrderController {
    @Autowired
    private orderService orderService;

    public OrderController(orderService orderService) {
        this.orderService = orderService;
    }

    @CrossOrigin
    @PostMapping("/order")
    public String receiveOrder(@RequestBody Map<String, Object> data) {
        List<Map<String, Object>> book = (List<Map<String, Object>>) data.get("book");
        String Uid = (String) data.get("uid");
        System.out.println(book);
        System.out.println(Uid);
        orderService.addOrder(book, Uid);
        return Uid;
    }

    @CrossOrigin
    @PostMapping("/getOrders")
    public List<Map<String, Object>> getOrders(@RequestParam String Uid) {
        return orderService.getOrders(Uid);
    }

    @CrossOrigin
    @PostMapping ("/getAllOrders")
    public List<Map<String, Object>> getAllOrders() {
        return orderService.getAllOrders();
    }

    @CrossOrigin
    @PostMapping("/search")
    public List<Map<String, Object>> search(@RequestParam String keyword) {
        return orderService.search(keyword);
    }

    @CrossOrigin
    @PostMapping("/searchMy")
    public List<Map<String, Object>> searchMy(@RequestParam String keyword, @RequestParam String Uid) {
        return orderService.searchMy(keyword, Uid);
    }
}

