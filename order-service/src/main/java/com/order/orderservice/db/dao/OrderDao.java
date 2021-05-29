package com.order.orderservice.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.order.orderservice.db.DbOperation;
import com.order.orderservice.db.dao.model.Order;
import com.order.orderservice.db.dao.repository.OrderRepository;
import com.order.orderservice.exception.OrderServiceException;


/**
 * @author DavidJMartin
 */
@Component
public class OrderDao implements DbOperation<Order> {

    private static String ORDER_ID_DOES_NOT_EXIST = "order with id: %s does not exist.";

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order findById(long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new OrderServiceException(String.format(ORDER_ID_DOES_NOT_EXIST, id)));
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order entity) {
        return orderRepository.saveAndFlush(entity);
    }

    @Override
    public Order update(Order order) {
        if(orderRepository.existsById(order.getId())) {
            return orderRepository.save(order);
        } else {
            throw new OrderServiceException(String.format(ORDER_ID_DOES_NOT_EXIST, order.getCustomerId()));
        }
    }

    @Override
    public void deleteById(long id) {
        if(orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new OrderServiceException(String.format(ORDER_ID_DOES_NOT_EXIST, id));
        }
    }

    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
    }

}
