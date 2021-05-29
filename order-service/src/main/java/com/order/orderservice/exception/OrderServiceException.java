package com.order.orderservice.exception;

/**
 * @author DavidJMartin
 */
public class OrderServiceException extends RuntimeException {
    public OrderServiceException(String message) {
        super(message);
    }
}
