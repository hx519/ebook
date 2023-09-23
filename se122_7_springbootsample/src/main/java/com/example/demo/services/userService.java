package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.utils.Msg;

import java.util.Map;
import java.util.List;

public interface userService {
    User check(String username, String password);
    boolean register(Map<String, String> input);
    List<User> getAllUsers();
    boolean changeMode(String uid);
    Msg logout();
}
