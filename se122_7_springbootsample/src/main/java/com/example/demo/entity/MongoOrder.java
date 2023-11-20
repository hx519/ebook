package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "mongoOrder")
public class MongoOrder {
    @Id
    private String id;
    private String uid;
    private String time;
    private String price;
    private String username;
    private List<Map<String, String>> orderItems;
}
