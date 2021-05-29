package com.order.orderservice.exception;

import java.util.function.Supplier;

/**
 * @author DavidJMartin
 */
public class OrderServiceException extends RuntimeException {

    public OrderServiceException(String message) {
        super(message);
    }

}
