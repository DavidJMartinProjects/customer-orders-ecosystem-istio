package com.orders.customerservice.exception;

/**
 * @author davidjmartin
 */
public class CustomerServiceException extends RuntimeException {
    public CustomerServiceException(String message) {
        super(message);
    }
}