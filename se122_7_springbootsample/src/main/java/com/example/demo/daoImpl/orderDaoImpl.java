package com.example.demo.daoImpl;

import com.example.demo.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.demo.entity.MyOrder;
import com.example.demo.entity.OrderItem;
import java.util.HashMap;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private UserRepository userRepository;

    public List<List<OrderItem>> getOrders(String Uid){
        List<List<OrderItem>> list = new ArrayList<>();
        list.clear();
        Long uid = Long.parseLong(Uid);
        List<MyOrder> orders = orderRepository.findAllByUser(userRepository.findUserByUid(uid));
        System.out.println(orders);
        for(MyOrder order: orders){
            List<OrderItem> orderItems = orderItemRepository.findAllByOrder(order);
            list.add(orderItems);
        }
        return list;
    }

    public void addOrder(List<Map<String, Object>> order, String Uid){
        Long uid = Long.parseLong(Uid);
        MyOrder myOrder = new MyOrder();
        myOrder.setUser(userRepository.findUserByUid(uid));
        myOrder.setUid(uid);
        orderRepository.save(myOrder);
        Long oid;
        for(Map<String, Object> item: order){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(myOrder);
//            OrderItem.setOid((Long)myOrder.getOid());
            orderItem.setTitle((String) item.get("title"));
            orderItem.setAuthor((String) item.get("author"));
            orderItem.setPrice((String) item.get("price"));
            orderItem.setQuantity("1");
            oid = myOrder.getOid();
            orderItem.setOid(oid);
//            orderItem.setOid(myOrder.getOid());
            orderItemRepository.save(orderItem);
        }

    }

    public MyOrder addMyOrder(String uid, String year, String month, String day, String hour, String minute, String total_price)
    {
        MyOrder myOrder = new MyOrder();
        myOrder.setUser(userRepository.findUserByUid(Long.parseLong(uid)));
        myOrder.setUid(Long.parseLong(uid));
        myOrder.setYear(year);
        myOrder.setMonth(month);
        myOrder.setDay(day);
        myOrder.setHour(hour);
        myOrder.setMinute(minute);
        myOrder.setPrice(total_price);
        myOrder.setUsername(userRepository.findUserByUid(Long.parseLong(uid)).getUsername());
        orderRepository.save(myOrder);
        return myOrder;
    }

//    public void addOrderItem(String oid, String title, String author, String price, String quantity)
//    {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setOid(Long.parseLong(oid));
//        orderItem.setTitle(title);
//        orderItem.setAuthor(author);
//        orderItem.setPrice(price);
//        orderItem.setQuantity(quantity);
//        orderItemRepository.save(orderItem);
//    }

    public List<MyOrder> getMyOrder(String Uid)
    {
        Long uid = Long.parseLong(Uid);
        List<MyOrder> myOrders = orderRepository.findAllByUser(userRepository.findUserByUid(uid));
        return myOrders;
    }

    public List<OrderItem> getOrderItem(String oid)
    {
        Long id = Long.parseLong(oid);
        List<OrderItem> orderItems = orderItemRepository.findAllByOrder(orderRepository.findByOid(id));
        return orderItems;
    }

    public List<MyOrder> getAllMyOrder()
    {
        List<MyOrder> myOrders = orderRepository.findAll();
        return myOrders;
    }
}
