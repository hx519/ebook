package com.example.demo.daoImpl;

import com.example.demo.dao.MongoOrderDao;
import com.example.demo.entity.MongoOrder;
import com.example.demo.entity.User;
import com.example.demo.repository.MongoOrderRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MongoOrderDaoImpl implements MongoOrderDao {
    @Autowired
    private MongoOrderRepository mongoOrderRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public MongoOrder addOrder(List<Map<String, String>> orderItems, String uid, String price, String time) {
        MongoOrder mongoOrder = new MongoOrder();
        mongoOrder.setUid(uid);
        User user = userRepository.findUserByUid(Long.parseLong(uid));
        mongoOrder.setUsername(user.getUsername());
        mongoOrder.setPrice(price);
        mongoOrder.setTime(time);
        mongoOrder.setOrderItems(orderItems);
        mongoOrderRepository.save(mongoOrder);
        return mongoOrder;
    }

    @Override
    public List<MongoOrder> getMyOrder(String uid) {
        return mongoOrderRepository.findMongoOrdersByUid(uid);
    }

    @Override
    public List<MongoOrder> getAllOrder() {
        return mongoOrderRepository.findAll();
    }

}
