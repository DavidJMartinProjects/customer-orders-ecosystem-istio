package com.order.orderservice.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.order.orderservice.db.DbOperation;
import com.order.orderservice.db.dao.model.CustomerOrder;
import com.order.orderservice.db.dao.repository.OrderRepository;


/**
 * @author DavidJMartin
 */
public class OrderDao implements DbOperation<CustomerOrder> {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public CustomerOrder save(CustomerOrder entity) {
        return orderRepository.save(entity);
    }

    @Override
    public CustomerOrder findById(long id) {
        return null;
    }

    @Override
    public List<CustomerOrder> findAll() {
        return null;
    }

    @Override
    public CustomerOrder update(CustomerOrder entity) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
