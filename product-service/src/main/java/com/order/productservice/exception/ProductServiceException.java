package com.order.productservice.exception;

/**
 * @author DavidJMartin
 */
public class ProductServiceException extends RuntimeException {
    public ProductServiceException(String message) {
        super(message);
    }
}
