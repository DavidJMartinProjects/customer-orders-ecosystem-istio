package com.order.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.order.orderservice.db.dao.model.CustomerOrder;
import com.order.orderservice.service.OrderService;


/**
 * @author DavidJMartin
 */
@RestController
@RequestMapping(OrderController.ORDER_BASE_PATH)
public class OrderController {

    public static final String ORDER_BASE_PATH = "/order";

    @Autowired
    private OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getStatus() {
        return "order-service is online.";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerOrder placeOrder(@Validated @RequestBody CustomerOrder customerOrder) {
        return orderService.placeOrder(customerOrder);
    }

}
