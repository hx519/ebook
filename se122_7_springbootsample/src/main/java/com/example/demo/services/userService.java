package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.utils.Msg;

import java.util.Map;
import java.util.List;

public interface UserService {
    Msg check(String username, String password);
    Msg register(Map<String, String> input);
    Msg getAllUsers();
    Msg changeMode(String uid);
    Msg logout();
}
