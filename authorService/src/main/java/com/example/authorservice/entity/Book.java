package com.example.authorservice.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name ="book")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"bid", "title", "author", "type", "price", "inventory", "description", "label", "image"})
public class Book {
    @Id
    @Column(name = "bid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bid;
    private String title;
    private String author;
    private String type;
    private String price;
    private String inventory;
    private String description;
    private String isbn;
    private String image;

    @Override
    public String toString() {
        return String.format(
                "Book[id=%d, title='%s', author='%s', type='%s', price='%s', inventory='%s', description='%s', isbn='%s', image='%s']",
                bid, title, author, type, price, inventory, description, isbn, image);
    }
}

