package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.example.demo.services.TimerService;
import com.example.demo.services.userService;
import com.example.demo.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class TimerController {
    @Autowired
    private userService userService;
    @Autowired
    private WebApplicationContext applicationContext;

    @PostMapping("/login")
    public User login(HttpSession httpSession, @RequestBody Map<String, String> input){
        // 判断用户名和密码是否符合user_password表中的记录
        User user = userService.check(input.get("user"), input.get("pwd"));
        if(user != null){
            TimerService timerService = applicationContext.getBean(TimerService.class);
            timerService.start();
//            System.out.println("session id: " + httpSession.getId());
            System.out.println(timerService);
            System.out.println(this);
            return user;
        }
        else{
            return null;
        }
    }

    @RequestMapping("/logout")
    public ResponseEntity<Msg> logout(HttpSession httpSession) {
        Msg result = userService.logout();
        if (result.getStatus() >= 0) {
            TimerService timerService = applicationContext.getBean(TimerService.class);
            long time = timerService.stop();
            result.setData(time);
//            System.out.println("session id: " + httpSession.getId());
            System.out.println(timerService);
            System.out.println(this);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }
}
