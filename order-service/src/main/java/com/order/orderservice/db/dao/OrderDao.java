package com.order.orderservice.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.order.orderservice.db.DbOperation;
import com.order.orderservice.db.dao.model.CustomerOrder;
import com.order.orderservice.db.dao.repository.OrderRepository;
import com.order.orderservice.exception.OrderServiceException;


/**
 * @author DavidJMartin
 */
@Component
public class OrderDao implements DbOperation<CustomerOrder> {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public CustomerOrder save(CustomerOrder entity) {
        return orderRepository.save(entity);
    }

    @Override
    public CustomerOrder findById(long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new OrderServiceException("order with id: " + id + " does not exist."));
    }

    @Override
    public List<CustomerOrder> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public CustomerOrder update(CustomerOrder customerOrder) {
        if(orderRepository.existsById(customerOrder.getId())) {
            return orderRepository.save(customerOrder);
        } else {
            throw new OrderServiceException("order with id: " + customerOrder.getId() + " does not exist.");
        }
    }

    @Override
    public void deleteById(long id) {
        if(orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new OrderServiceException("order with id: " + id + " does not exist.");
        }
    }
}
