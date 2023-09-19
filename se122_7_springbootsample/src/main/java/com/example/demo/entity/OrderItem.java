package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="orderitem")
public class OrderItem {
    @Id
    @Column(name = "iid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;
    @Column(name = "oid")
    private Long oid;
    private String title;
    private String author;
    private String quantity;
    private String price;
    @ManyToOne
    @JoinColumn(name = "oid", insertable = false, updatable = false)
    private MyOrder order;

    public void setOid(Long oid) {
        this.oid = oid;
    }
}
