package com.example.demo.servicesImpl;

import com.example.demo.entity.Book;
import com.example.demo.kafka.ConsumerService;
import com.example.demo.kafka.ProducerService;
import com.example.demo.services.orderService;
import com.example.demo.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.orderRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
import java.util.Date;

import com.example.demo.entity.MyOrder;
import com.example.demo.entity.OrderItem;
import com.example.demo.dao.orderDao;
import com.example.demo.dao.bookDao;

@Service
public class orderServiceImpl implements orderService{
    @Autowired
    private  orderDao orderDao;
    @Autowired
    private bookDao bookDao;
    @Autowired
    private ProducerService producerService;

    public List<Map<String, Object>> getOrders(String Uid)
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
        return orders;
    }

    public void addOrder(List<Map<String, Object>> order, String Uid){
        long time = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        String[] date = df.format(time).split("-");

        // 获取总价并维护每本书的库存
        long total = 0;
        for(Map<String, Object> item: order){
            total += Long.parseLong((String) item.get("price")) * Long.parseLong((String) item.get("quantity"));
            bookDao.updateInventory(Long.parseLong((String) item.get("bid")), (String) item.get("quantity"));
        }

        MyOrder myOrder = orderDao.addMyOrder(Uid, date[0], date[1], date[2], date[3], date[4], String.valueOf(total));
        Long oid = myOrder.getOid();
        for(Map<String, Object> item: order){
            orderDao.addOrderItem(String.valueOf(oid), (String) item.get("title"), (String) item.get("author"), (String) item.get("price"), (String) item.get("quantity"));
        }
        System.out.println("数据库添加订单成功");
        producerService.sendMessage("Ordered-topic", "Ordered");

    }

    public List<Map<String, Object>> getAllOrders()
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
        return orders;
    }

    public List<Map<String, Object>> search(String keyword) {
        List<Map<String, Object>> all = getAllOrders();
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
        return result;
    }

    public List<Map<String, Object>> searchMy(String keyword, String Uid) {
        List<Map<String, Object>> all = getOrders(Uid);
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
        return result;
    }

    public Msg placeOrder(Map<String, Object> data) {
        producerService.sendMessage("placeOrder-topic", data.toString());
        return new Msg(1, "发送订单消息成功");
    }


}
