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
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<MyOrder> orders;

//    public user(Long Uid, String username, String phone, String address, String email, String avatar, String mode) {
//        this.Uid = Uid;
//        this.username = username;
//        this.phone = phone;
//        this.address = address;
//        this.email = email;
//        this.avatar = avatar;
//        this.mode = mode;
//    }
//
//    public Long getId() {
//        return Uid;
//    }
//    public void setId(Long id) {
//        this.Uid = id;
//    }
//    public String getUsername() {
//        return username;
//    }
//    public void setUsername(String name) {
//        this.username = name;
//    }
//    public String getPhone() {
//        return phone;
//    }
//    public void setPhone(String name) {
//        this.phone = name;
//    }
//    public String getAddress() {
//        return address;
//    }
//    public void setAddress(String name) {
//        this.address = name;
//    }
//    public String getEmail() {
//        return email;
//    }
//    public void setEmail(String name) {
//        this.email = name;
//    }
//    public String getAvatar() {
//        return avatar;
//    }
//    public void setAvatar(String name) {
//        this.avatar = name;
//    }
//    public String getMode() {
//        return mode;
//    }
//    public void setMode(String name) {
//        this.mode = name;
//    }

    @Override
    public String toString() {
        return String.format(
                "user[uid='%s', username='%s', phone='%s', address='%s', email='%s', avatar='%s', mode='%s']",
                uid, username, phone, address, email, avatar, mode);
    }
}
