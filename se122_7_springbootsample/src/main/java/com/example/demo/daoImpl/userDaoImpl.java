package com.example.demo.daoImpl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.repository.UserRepository;
import com.example.demo.entity.UserPassword;
import com.example.demo.repository.UserPasswordRepository;

import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPasswordRepository userPasswordRepository;

    @Override
    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    @Override
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

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User findUserByUid(Long uid){
        return userRepository.findUserByUid(uid);
    }

    @Override
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

    @Override
    public UserPassword findUserPasswordByUsername(String username){
        User user = userRepository.findUserByUsername(username);
        if(user == null){
            return null;
        }
        Long uid = user.getUid();
        return userPasswordRepository.findUserpasswordByUid(uid);
    }
}
