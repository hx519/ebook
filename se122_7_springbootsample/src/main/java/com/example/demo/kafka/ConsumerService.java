package com.example.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @KafkaListener(topics = "placeOrder-topic", groupId = "my-group")
    public void receivePlaceOrderMessage(String message) {
        // 处理接收到的消息
        System.out.println("Received message: " + message);
    }

    @KafkaListener(topics = "Ordered-topic", groupId = "my-group")
    public void receiveOrderedMessage(String message) {
        // 处理接收到的消息
        System.out.println("Received message: " + message);
    }
}

