package com.order.orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.orderservice.db.DbOperation;
import com.order.orderservice.db.dao.model.Order;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DavidJMartin
 */
@Service
@Slf4j
public class OrderService {

    @Autowired
    private DbOperation<Order> dbOperation;

    public Order placeOrder(Order order) {
        return dbOperation.save(order);
    }

    public Order updateOrder(Order order) {
        return dbOperation.update(order);
    }

    public void deleteOrderById(Long id) {
        dbOperation.deleteById(id);
    }

    public List<Order> findAll() {
        return dbOperation.findAll();
    }

    public Order findById(long id) {
        return dbOperation.findById(id);
    }

}
