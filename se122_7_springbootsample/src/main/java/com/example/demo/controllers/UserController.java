package com.example.demo.controllers;
import com.example.demo.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.User;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private  userService userService;

    public UserController(userService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> input){
        // 判断用户名和密码是否符合user_password表中的记录
        User user = userService.check(input.get("user"), input.get("pwd"));
        if(user != null){
            return user;
        }
        else{
            return null;
        }

    }

    @CrossOrigin
    @PostMapping("/register")
    public boolean register(@RequestBody Map<String, String> input){
        boolean flag = userService.register(input);
        return flag;
    }

    @CrossOrigin
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @CrossOrigin
    @PostMapping("/changeMode")
    public boolean changeMode(@RequestBody Map<String, String> uid){
        return userService.changeMode(uid.get("uid"));
    }
}
