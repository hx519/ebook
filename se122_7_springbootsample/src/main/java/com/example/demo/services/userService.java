package com.example.demo.services;

import com.example.demo.entity.User;

import java.util.Map;
import java.util.List;

public interface userService {
    public User check(String username, String password);
    public boolean register(Map<String, String> input);
    public List<User> getAllUsers();
    public boolean changeMode(String uid);
}
