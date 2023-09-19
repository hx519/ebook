package com.example.demo.daoImpl;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.repository.userRepository;
import com.example.demo.dao.userDao;
import com.example.demo.entity.UserPassword;
import com.example.demo.repository.userPasswordRepository;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class userDaoImpl implements userDao{
    @Autowired
    private userRepository userRepository;
    @Autowired
    private userPasswordRepository userPasswordRepository;

    public User checkUser(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if(user == null){
            return null;
        }
        Long uid = user.getUid();
        System.out.println(uid);
        UserPassword userpassword = userPasswordRepository.findUserpasswordByUid(uid);
//        System.out.println(user_password.getPassword());
        if(userpassword == null){
            return null;
        }
        if(userpassword.getPassword().equals(password)){
            return user;
        }
        else{
            return null;
        }
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public void addUser(Map<String, String> input){
        User user = new User();
        user.setUsername(input.get("username"));
        user.setAddress(input.get("address"));
        user.setPhone(input.get("phone"));
        user.setEmail(input.get("email"));
        user.setMode("user");
        user.setAvatar(input.get("avatar"));
        userRepository.save(user);

        UserPassword userpassword = new UserPassword();
        userpassword.setUid(findUserByUsername(input.get("username")).getUid());
        userpassword.setPassword(input.get("password"));
        userPasswordRepository.save(userpassword);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User findUserByUid(Long uid){
        return userRepository.findUserByUid(uid);
    }

    public boolean changeMode(String uid){
        User user = userRepository.findUserByUid(Long.parseLong(uid));
        if(user.getMode().equals("user")){
            user.setMode("ban");
        }
        else{
            user.setMode("user");
        }
        userRepository.save(user);
        return true;
    }
}
