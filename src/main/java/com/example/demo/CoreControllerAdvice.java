package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CoreControllerAdvice {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity catchError(RuntimeException e) {

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
