package com.orders.customerservice.exception;

/**
 * @author davidjmartin
 */
public class ValidationFailureException extends RuntimeException {
    public ValidationFailureException(String message) {
        super(message);
    }
}
