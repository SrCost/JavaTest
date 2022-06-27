package com.sigabem.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHadler {
	
	@ExceptionHandler(ResourceNotException.class)
    public ResponseEntity<?> resouceNotFoundExccption(ResourceNotException ex, WebRequest request) {
        ErroDetails erroDetails = new ErroDetails(new Date(), ex.getMessage(), request.getDescription(false));

        return  new ResponseEntity<>(erroDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHadler(ResourceNotException ex, WebRequest request) {
        ErroDetails erroDetails = new ErroDetails(new Date(), ex.getMessage(), request.getDescription(false));

        return  new ResponseEntity<>(erroDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
