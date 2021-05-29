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

import com.order.orderservice.db.dao.model.Order;
import com.order.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DavidJMartin
 */
@RestController
@RequestMapping(OrderController.ORDER_BASE_PATH)
@Slf4j
public class OrderController {

    public static final String ORDER_BASE_PATH = "/orders";

    @Autowired
    private OrderService orderService;

    @GetMapping("/ping")
    @ResponseStatus(HttpStatus.OK)
    public String getPing() {
        log.info("received ping request.");
        return "greetings from the order-service.";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping
    @RequestMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrderById(@PathVariable long id) {
        return orderService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order placeOrder(@Validated @RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Order updateOrder(@Validated @RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    @RequestMapping(name = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }

}
