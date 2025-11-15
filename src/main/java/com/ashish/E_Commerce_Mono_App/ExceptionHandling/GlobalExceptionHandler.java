package com.ashish.E_Commerce_Mono_App.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<String> handleOrderException(OrderException e){
        return new ResponseEntity<>("OrderException: "+e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
