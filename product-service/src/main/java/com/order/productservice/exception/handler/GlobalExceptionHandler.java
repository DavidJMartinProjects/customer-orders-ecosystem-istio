package com.order.productservice.exception.handler;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.order.productservice.exception.ProductServiceException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DavidJMartin
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorData handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException ex) {
        log.info("handling ValidationFailureException with propagated violation errors: {}.", ex.getMessage());
        List<String> errors = ex.getConstraintViolations()
            .stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.toList());

        return buildErrorData("request validation failure.", errors.toString(), request);
    }

    @ExceptionHandler(ProductServiceException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorData handleProductServiceException(HttpServletRequest request, ProductServiceException ex) {
        log.info("handling ProductServiceException: {}.", ex.getMessage());
        return buildErrorData("order-service error.", ex.getMessage(), request);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorData handleDataAccessException(HttpServletRequest request, EmptyResultDataAccessException ex) {
        log.info("handling EmptyResultDataAccessException: {}.", ex.getMessage());
        return buildErrorData("internal server error.", ex.getMessage(), request);
    }

    private ErrorData buildErrorData(String errorCode, String message, HttpServletRequest request) {
        return ErrorData.builder()
            .errorCode(errorCode)
            .message(message)
            .url(request.getMethod() + " request to : " + request.getRequestURI())
            .timestamp(LocalDateTime.now().toString())
            .build();
    }

}
