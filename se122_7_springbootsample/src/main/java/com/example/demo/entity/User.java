package com.example.demo.entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name ="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"uid", "username", "phone", "address", "email", "avatar", "mode"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    private String username;
    private String phone;
    private String address;
    private String email;
    private String avatar;
    private String mode;

    @Override
    public String toString() {
        return String.format(
                "user[uid='%s', username='%s', phone='%s', address='%s', email='%s', avatar='%s', mode='%s']",
                uid, username, phone, address, email, avatar, mode);
    }
}
