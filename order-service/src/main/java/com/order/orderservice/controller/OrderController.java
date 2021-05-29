package com.order.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    public static final String ORDER_BASE_PATH = "/orders";

    @Autowired
    private OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerOrder> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping
    @RequestMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerOrder getOrderById(@PathVariable long id) {
        return orderService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerOrder placeOrder(@Validated @RequestBody CustomerOrder customerOrder) {
        return orderService.placeOrder(customerOrder);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerOrder updateOrder(@Validated @RequestBody CustomerOrder customerOrder) {
        return orderService.updateOrder(customerOrder);
    }

    @RequestMapping(name = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }

}
