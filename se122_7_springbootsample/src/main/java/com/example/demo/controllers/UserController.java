package com.example.demo.controllers;
import com.example.demo.services.TimerService;
import com.example.demo.services.userService;
import com.example.demo.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.User;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;

@RestController
@Scope("session")
public class UserController {
    @Autowired
    private  userService userService;
//    WebApplicationContext applicationContext;

    @Autowired
    private TimerService timerService;

//    public UserController(userService userService) {
//        this.userService = userService;
//    }

    @CrossOrigin
    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> input){
//        userService userService = applicationContext.getBean(userService.class);
        // 判断用户名和密码是否符合user_password表中的记录
        User user = userService.check(input.get("user"), input.get("pwd"));
        if(user != null){
            timerService.start();
            System.out.println(timerService);
            System.out.println(this);
            return user;
        }
        else{
            return null;
        }
    }

    @CrossOrigin
    @RequestMapping("/logout")
    public ResponseEntity<Msg> logout() {
//        userService userService = applicationContext.getBean(userService.class);
        Msg result = userService.logout();
        if (result.getStatus() >= 0) {
            long time = timerService.stop();
            result.setData(time);
            System.out.println(timerService);
            System.out.println(this);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }

    @CrossOrigin
    @PostMapping("/register")
    public boolean register(@RequestBody Map<String, String> input){
//        userService userService = applicationContext.getBean(userService.class);
        boolean flag = userService.register(input);
        return flag;
    }

    @CrossOrigin
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
//        userService userService = applicationContext.getBean(userService.class);
        return userService.getAllUsers();
    }

    @CrossOrigin
    @PostMapping("/changeMode")
    public boolean changeMode(@RequestBody Map<String, String> uid){
//        userService userService = applicationContext.getBean(userService.class);
        return userService.changeMode(uid.get("uid"));
    }
}
