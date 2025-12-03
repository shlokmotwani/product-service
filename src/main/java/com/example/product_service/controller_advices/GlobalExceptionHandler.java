package com.example.product_service.controller_advices;

import com.example.product_service.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e){
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e){
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.NOT_FOUND);
    }
}
