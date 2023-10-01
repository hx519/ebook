package com.example.demo.kafka;

import com.example.demo.websocket.WebSocket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.example.demo.services.OrderService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ConsumerService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ObjectMapper objectMapper;
    @Resource
    private WebSocket webSocket;

    @KafkaListener(topics = "PlaceOrder-topic", groupId = "my-group")
    public void receivePlaceOrderMessage(String message) throws JsonProcessingException {
        // 处理接收到的消息
        System.out.println("Received message: " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> data = objectMapper.readValue(message, new TypeReference<Map<String, Object>>() {});
        List<Map<String, Object>> book = (List<Map<String, Object>>) data.get("book");
        String Uid = (String) data.get("uid");
        orderService.addOrder(book, Uid);
    }

    @KafkaListener(topics = "Ordered-topic", groupId = "my-group")
    public void receiveOrderedMessage(String message) throws JsonProcessingException {
        // 处理接收到的消息
        System.out.println("Received message: " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> data = objectMapper.readValue(message, new TypeReference<Map<String, Object>>() {});
        webSocket.sendOneMessage((String) data.get("uid"), message);
    }
}

