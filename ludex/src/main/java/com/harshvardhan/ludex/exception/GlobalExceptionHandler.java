package com.harshvardhan.ludex.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler (GameNotFoundException.class)
    public String handleGameNotFound(GameNotFoundException e)
    {
        return e.getMessage();
    }
    
}
