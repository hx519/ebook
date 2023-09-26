package com.example.demo.controllers;
import com.example.demo.services.UserService;
import com.example.demo.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.User;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private  UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Msg> register(@RequestBody Map<String, String> input){
        Msg result = userService.register(input);
        if(result.getStatus() >= 0){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Msg> getAllUsers(){
        Msg result = userService.getAllUsers();
        if(result.getStatus() >= 0){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @PostMapping("/changeMode")
    public ResponseEntity<Msg> changeMode(@RequestBody Map<String, String> input){
        Msg result = userService.changeMode(input.get("uid"));
        if(result.getStatus() >= 0){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }
}
