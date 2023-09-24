package com.example.demo.controllers;
import com.example.demo.kafka.ProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;
import com.example.demo.services.orderService;

@RestController
public class OrderController {
    @Autowired
    private orderService orderService;
    @Autowired
    private ProducerService producerService;

    public OrderController(orderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public String receiveOrder(@RequestBody Map<String, Object> data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(data);
        System.out.println("后端接收到的下订单请求");
        producerService.sendMessage("PlaceOrder-topic", jsonString);
        return "success";
    }

    @PostMapping("/getOrders")
    public List<Map<String, Object>> getOrders(@RequestParam String Uid) {
        return orderService.getOrders(Uid);
    }

    @PostMapping ("/getAllOrders")
    public List<Map<String, Object>> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/search")
    public List<Map<String, Object>> search(@RequestParam String keyword) {
        return orderService.search(keyword);
    }

    @PostMapping("/searchMy")
    public List<Map<String, Object>> searchMy(@RequestParam String keyword, @RequestParam String Uid) {
        return orderService.searchMy(keyword, Uid);
    }
}

