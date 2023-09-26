package com.example.demo.servicesImpl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserPassword;
import com.example.demo.services.UserService;
import com.example.demo.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.User;

import java.util.Map;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public Msg check(String username, String password){
        UserPassword userPassword = userDao.findUserPasswordByUsername(username);
        if(userPassword == null)
            return new Msg(-1, "user not exist");
        else if(!userPassword.getPassword().equals(password))
            return new Msg(-2, "wrong password");
        User user = userDao.findUserByUsername(username);
        return new Msg(1, "login success", user);
    }

    public Msg register(Map<String, String> input){
        User user = userDao.findUserByUsername(input.get("username"));
        if(user != null)
            return new Msg(-1, "user already exist");
        userDao.addUser(input);
        return new Msg(1, "register success");
    }

    public Msg getAllUsers(){
        List<User> users = userDao.getAllUsers();
        return new Msg(1, "get all users success", users);
    }

    public Msg changeMode(String uid){
        boolean result = userDao.changeMode(uid);
        if(result)
            return new Msg(1, "change mode success");
        else
            return new Msg(-1, "change mode failed");
    }

    public Msg logout() {
        return new Msg(1, "logout success");
    }
}
