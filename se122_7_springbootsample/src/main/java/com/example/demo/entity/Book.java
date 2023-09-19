package com.example.demo.entity;
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
//    public Book(Long Bid, String title, String author, String type, String price, String inventory, String description, String label, String image) {
//        this.Bid = Bid;
//        this.title = title;
//        this.author = author;
//        this.type = type;
//        this.price = price;
//        this.inventory = inventory;
//        this.description = description;
//        this.label = label;
//        this.image = image;
//    }
//
//
//    public Long getId() {
//        return Bid;
//    }
//    public void setId(Long id) {
//        this.Bid = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//    public String getType() {
//        return type;
//    }
//    public void setType(String type) {
//        this.type = type;
//    }
//    public String getPrice() {
//        return price;
//    }
//    public void setPrice(String price) {
//        this.price = price;
//    }
//    public String getInventory() {
//        return inventory;
//    }
//    public void setInventory(String inventory) {
//        this.inventory = inventory;
//    }
//    public String getDescription() {
//        return description;
//    }
//    public void setDescription(String description) {
//        this.description = description;
//    }
//    public String getLabel() {
//        return label;
//    }
//    public void setLabel(String label) {
//        this.label = label;
//    }
//    public String getImage() {
//        return image;
//    }
//    public void setImage(String image) {
//        this.image = image;
//    }


    @Override
    public String toString() {
        return String.format(
                "Book[id=%d, title='%s', author='%s', type='%s', price='%s', inventory='%s', description='%s', isbn='%s', image='%s']",
                bid, title, author, type, price, inventory, description, isbn, image);
    }
}
