package com.order.orderservice.factories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.order.orderservice.db.DbOperation;
import com.order.orderservice.db.dao.model.Order;
import com.order.orderservice.db.dao.model.OrderItem;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DavidJMartin
 */
@Slf4j
@Component
public class CustomerOrderFactory {

    @Autowired
    private DbOperation<Order> dbOperation;

    public void persistTestData(int numOfOrders) {
        List<Order> orders = buildTestCustomerOrders(numOfOrders);
        orders.forEach(dbOperation::save);
        log.info("saved test orders.");
    }

    public List<Order> buildTestCustomerOrders(int numOfOrders) {
        List<Order> orders = new ArrayList<>();
        for(int index = 1; index <= numOfOrders + 1; index++) {
            Order order =
                Order.builder()
                    .customerId(index)
                    .orderItems(buildOrderItems(4))
                    .status("order placed successfully.")
                    .build();
            orders.add(order);
        }
        log.info("Built {}", orders);
        return orders;
    }

    private Set<OrderItem> buildOrderItems(int numOfOrderItems) {
        Set<OrderItem> orderItems = new HashSet<>();
        for(int index = 1; index <= numOfOrderItems; index++) {
            OrderItem orderItem =
                OrderItem.builder()
                    .productId(index)
                    .quantity(10 + index)
                    .build();
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    public Order findCustomerOrderById(int id) {
        return dbOperation.findById(id);
    }

    public void deleteTestData() {
        dbOperation.deleteAll();
    }

}
