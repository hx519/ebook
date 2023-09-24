package com.example.demo.controllers;
import com.example.demo.services.TimerService;
import com.example.demo.services.userService;
import com.example.demo.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.User;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private  userService userService;

    @PostMapping("/register")
    public boolean register(@RequestBody Map<String, String> input){
        boolean flag = userService.register(input);
        return flag;
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/changeMode")
    public boolean changeMode(@RequestBody Map<String, String> uid){
        return userService.changeMode(uid.get("uid"));
    }
}
