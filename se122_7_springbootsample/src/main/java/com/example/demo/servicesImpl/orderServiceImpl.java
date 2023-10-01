package com.example.demo.servicesImpl;

import com.example.demo.dao.BookDao;
import com.example.demo.dao.OrderDao;
import com.example.demo.dao.OrderItemDao;
import com.example.demo.kafka.ProducerService;
import com.example.demo.services.OrderService;
import com.example.demo.utils.Msg;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
import java.util.Date;

import com.example.demo.entity.MyOrder;
import com.example.demo.entity.OrderItem;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private ProducerService producerService;

    @Override
    public Msg receiveOrder(Map<String, Object> data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(data);
        System.out.println("后端接收到的下订单请求");
        producerService.sendMessage("PlaceOrder-topic", jsonString);
        return new Msg(1, "下订单请求发送成功");
    }

    @Override
    public Msg getOrders(String Uid)
    {
        List<MyOrder> myOrders = orderDao.getMyOrder(Uid);
        List<Map<String, Object>> orders = new ArrayList<>();
        for (MyOrder myOrder: myOrders){
            Map<String, Object> order = new java.util.HashMap<>();
            order.put("oid", myOrder.getOid());
            order.put("year", myOrder.getYear());
            order.put("month", myOrder.getMonth());
            order.put("day", myOrder.getDay());
            order.put("hour", myOrder.getHour());
            order.put("minute", myOrder.getMinute());
            order.put("price", myOrder.getPrice());
            order.put("username", myOrder.getUsername());

            List<OrderItem> orderItems = orderDao.getOrderItem(String.valueOf(myOrder.getOid()));
            List<Map<String, Object>> items = new ArrayList<>();

            for (OrderItem orderItem: orderItems){
                Map<String, Object> item = new java.util.HashMap<>();
                item.put("title", orderItem.getTitle());
                item.put("author", orderItem.getAuthor());
                item.put("price", orderItem.getPrice());
                item.put("quantity", orderItem.getQuantity());
                items.add(item);
            }

            order.put("items", items);
            orders.add(order);
        }
        return new Msg(1, "获取订单成功", orders);
    }

    @Override
    @Transactional
    public void addOrder(List<Map<String, Object>> order, String Uid) throws JsonProcessingException {
            long time = System.currentTimeMillis();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
            String[] date = df.format(time).split("-");
            System.out.println("当前时间：" + df.format(time));
            // 获取总价并维护每本书的库存
            long total = 0;
            for (Map<String, Object> item : order) {
                total += Long.parseLong((String) item.get("price")) * Long.parseLong((String) item.get("quantity"));
                bookDao.updateInventory(Long.parseLong((String) item.get("bid")), (String) item.get("quantity"));
            }
            System.out.println("库存更新成功");
            // 添加订单
            MyOrder myOrder = orderDao.addMyOrder(Uid, date[0], date[1], date[2], date[3], date[4], String.valueOf(total));
            System.out.println("数据库添加订单成功");
            Long oid = myOrder.getOid();
            // 添加订单项
            for (Map<String, Object> item : order) {
                orderItemDao.addOrderItem(String.valueOf(oid), (String) item.get("title"), (String) item.get("author"), (String) item.get("price"), (String) item.get("quantity"));
            }
            System.out.println("数据库添加订单项成功");

            // 发送消息
        Map<String, Object> data = new java.util.HashMap<>();
        data.put("uid", Uid);
//        MyOrder newOrder = orderDao.getMyOrder(String.valueOf(oid)).get(0);
        MyOrder newOrder = myOrder;
        data.put("time", newOrder.getYear() + "-" + newOrder.getMonth() + "-" + newOrder.getDay() + " " + newOrder.getHour() + ":" + newOrder.getMinute());
        data.put("price", newOrder.getPrice());
        List<OrderItem> orderItems = orderDao.getOrderItem(String.valueOf(oid));
        List<Map<String, Object>> items = new ArrayList<>();
        for (OrderItem orderItem: orderItems){
            Map<String, Object> item = new java.util.HashMap<>();
            item.put("title", orderItem.getTitle());
            item.put("author", orderItem.getAuthor());
            item.put("price", orderItem.getPrice());
            item.put("quantity", orderItem.getQuantity());
            items.add(item);
        }
        data.put("items", items);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(data);
        producerService.sendMessage("Ordered-topic", jsonString);

    }

    @Override
    public Msg getAllOrders()
    {
        List<MyOrder> myOrders = orderDao.getAllMyOrder();
        List<Map<String, Object>> orders = new ArrayList<>();
        for (MyOrder myOrder: myOrders){
            Map<String, Object> order = new java.util.HashMap<>();
            order.put("oid", myOrder.getOid());
            order.put("year", myOrder.getYear());
            order.put("month", myOrder.getMonth());
            order.put("day", myOrder.getDay());
            order.put("hour", myOrder.getHour());
            order.put("minute", myOrder.getMinute());
            order.put("price", myOrder.getPrice());
            order.put("username", myOrder.getUsername());

            List<OrderItem> orderItems = orderDao.getOrderItem(String.valueOf(myOrder.getOid()));
            List<Map<String, Object>> items = new ArrayList<>();

            for (OrderItem orderItem: orderItems){
                Map<String, Object> item = new java.util.HashMap<>();
                item.put("title", orderItem.getTitle());
                item.put("author", orderItem.getAuthor());
                item.put("price", orderItem.getPrice());
                item.put("quantity", orderItem.getQuantity());
                items.add(item);
            }

            order.put("items", items);
            orders.add(order);
        }
        return new Msg(1, "获取全部订单成功", orders);
    }

    @Override
    public Msg search(String keyword) {
        List<Map<String, Object>> all = (List<Map<String, Object>>) getAllOrders().getData();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> order : all) {
            List<Map<String, Object>> items = (List<Map<String, Object>>) order.get("items");
            for (Map<String, Object> item : items) {
                if (((String) item.get("title")).contains(keyword) || ((String) item.get("author")).contains(keyword)) {
                    result.add(order);
                    break;
                }
            }

            // 处理时间范围搜索
            String[] date = keyword.split("-");
            if (date.length == 2) {
                String[] min = date[0].split("/");
                String[] max = date[1].split("/");
                Timestamp minTime = Timestamp.valueOf(min[0] + "-" + min[1] + "-" + min[2] + " 00:00:00");
                Timestamp maxTime = Timestamp.valueOf(max[0] + "-" + max[1] + "-" + max[2] + " 23:59:59");
                Timestamp orderTime = Timestamp.valueOf(order.get("year") + "-" + order.get("month") + "-" + order.get("day") + " 12:00:00");
                if (orderTime.after(minTime) && orderTime.before(maxTime)) {
                    result.add(order);
                }
            }

        }
        return new Msg(1, "搜索成功", result);
    }

    @Override
    public Msg searchMy(String keyword, String Uid) {
        List<Map<String, Object>> all = (List<Map<String, Object>>) getOrders(Uid).getData();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> order : all) {
            List<Map<String, Object>> items = (List<Map<String, Object>>) order.get("items");
            for (Map<String, Object> item : items) {
                if (((String) item.get("title")).contains(keyword) ) {
                    result.add(order);
                    break;
                }
            }

            // 处理时间范围搜索
            String[] date = keyword.split("-");
            if (date.length == 2) {
                String[] min = date[0].split("/");
                String[] max = date[1].split("/");
                Timestamp minTime = Timestamp.valueOf(min[0] + "-" + min[1] + "-" + min[2] + " 00:00:00");
                Timestamp maxTime = Timestamp.valueOf(max[0] + "-" + max[1] + "-" + max[2] + " 23:59:59");
                Timestamp orderTime = Timestamp.valueOf(order.get("year") + "-" + order.get("month") + "-" + order.get("day") + " 12:00:00");
                if (orderTime.after(minTime) && orderTime.before(maxTime)) {
                    result.add(order);
                }
            }

        }
        return new Msg(1, "搜索成功", result);
    }



}
