package com.order.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.order.orderservice.db.DbOperation;
import com.order.orderservice.db.dao.model.CustomerOrder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DavidJMartin
 */
@Service
@Slf4j
public class OrderService {

    @Autowired
    private DbOperation<CustomerOrder> dbOperation;

    public CustomerOrder placeOrder(CustomerOrder customerOrder) {
        return dbOperation.save(customerOrder);
    }

}
