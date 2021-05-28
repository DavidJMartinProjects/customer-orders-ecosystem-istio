package com.orders.customerservice.exception;

/**
 * @author davidjmartin
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}