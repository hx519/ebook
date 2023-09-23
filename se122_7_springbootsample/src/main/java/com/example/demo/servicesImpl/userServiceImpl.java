package com.example.demo.servicesImpl;

import com.example.demo.services.userService;
import com.example.demo.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.userDao;
import com.example.demo.entity.User;

import java.util.Map;
import java.util.List;

@Service
public class userServiceImpl implements userService{
    @Autowired
    private  userDao userDao;

    public User check(String username, String password){
        return userDao.checkUser(username, password);
    }

    public boolean register(Map<String, String> input){
        User user = userDao.findUserByUsername(input.get("username"));
        if(user != null)
            return false;

        userDao.addUser(input);
        return true;
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public boolean changeMode(String uid){
        return userDao.changeMode(uid);
    }

    public Msg logout() {
        return new Msg(1, "logout success");
    }
}
