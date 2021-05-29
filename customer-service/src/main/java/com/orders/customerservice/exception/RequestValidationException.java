package com.orders.customerservice.exception;

/**
 * @author davidjmartin
 */
public class RequestValidationException extends RuntimeException {
    public RequestValidationException(String message) {
        super(message);
    }
}
