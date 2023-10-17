package com.example.demo.controllers;
import com.example.demo.kafka.ProducerService;
import com.example.demo.services.OrderService;
import com.example.demo.utils.Msg;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/receiveOrder")
    public ResponseEntity<Msg> receiveOrder(@RequestBody Map<String, Object> data) throws JsonProcessingException {
        Msg result = orderService.receiveOrder(data);
        if(result.getStatus() >= 0){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @PostMapping("/getOrders")
    public ResponseEntity<Msg> getOrders(@RequestParam String Uid) {
        Msg result = orderService.getOrders(Uid);
        if(result.getStatus() >= 0){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @PostMapping("/getAllOrders")
    public ResponseEntity<Msg> getAllOrders() {
        Msg result = orderService.getAllOrders();
        if(result.getStatus() >= 0){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<Msg> search(@RequestParam String keyword) {
        Msg result = orderService.search(keyword);
        if(result.getStatus() >= 0){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @PostMapping("/searchMy")
    public ResponseEntity<Msg> searchMy(@RequestParam String keyword, @RequestParam String Uid) {
        Msg result = orderService.searchMy(keyword, Uid);
        if(result.getStatus() >= 0){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

}

