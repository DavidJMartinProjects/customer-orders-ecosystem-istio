package com.order.orderservice.factories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.order.orderservice.db.DbOperation;
import com.order.orderservice.db.dao.model.CustomerOrder;
import com.order.orderservice.db.dao.model.OrderItem;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DavidJMartin
 */
@Slf4j
@Component
public class CustomerOrderFactory {

    @Autowired
    private DbOperation<CustomerOrder> dbOperation;

    public void persistTestData(int numOfOrders) {
        List<CustomerOrder> customerOrders = buildTestCustomerOrders(numOfOrders);
        customerOrders.forEach(dbOperation::save);
        log.info("saved test orders.");
    }

    public List<CustomerOrder> buildTestCustomerOrders(int numOfOrders) {
        List<CustomerOrder> customerOrders = new ArrayList<>();
        for(int index = 1; index <= numOfOrders + 1; index++) {
            CustomerOrder customerOrder =
                CustomerOrder.builder()
                    .customerId(index)
                    .orderItems(buildOrderItems(4))
                    .status("order placed successfully.")
                    .build();
            customerOrders.add(customerOrder);
        }
        log.info("Built {}", customerOrders);
        return customerOrders;
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

    public CustomerOrder findCustomerOrderById(int id) {
        return dbOperation.findById(id);
    }

    public void deleteTestData() {
        dbOperation.deleteAll();
    }

}
