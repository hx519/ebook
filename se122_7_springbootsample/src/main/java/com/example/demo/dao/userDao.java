package com.example.demo.dao;
import com.example.demo.entity.User;
import com.example.demo.entity.UserPassword;

import java.util.List;
import java.util.Map;

public interface UserDao {
    User findUserByUsername(String username);
    void addUser(Map<String, String> input);
    List<User> getAllUsers();
    User findUserByUid(Long uid);
    boolean changeMode(String uid);
    UserPassword findUserPasswordByUsername(String username);
}
