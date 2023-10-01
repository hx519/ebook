package com.example.demo.daoImpl;

import com.example.demo.dao.OrderItemDao;
import com.example.demo.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @Autowired
    private com.example.demo.repository.OrderItemRepository orderItemRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
//    @Transactional
    public void addOrderItem(String oid, String title, String author, String price, String quantity)
    {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(Long.parseLong(oid));
        orderItem.setTitle(title);
        orderItem.setAuthor(author);
        orderItem.setPrice(price);
        orderItem.setQuantity(quantity);
        orderItemRepository.save(orderItem);
//        int result = 10 / 0;
    }

}
