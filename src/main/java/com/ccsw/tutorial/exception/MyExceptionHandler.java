package com.ccsw.tutorial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author caliagaq
 *
 */
@ControllerAdvice
public class MyExceptionHandler {
    
    @ExceptionHandler(ClientNameAlreadyExistsException.class)
    public ResponseEntity<?> clientNameAlreadyExistsExceptionHandling(Exception exception, WebRequest request) {
        Error errorThrown = new Error (HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(errorThrown, errorThrown.getHttpStatus());
    }
    
    @ExceptionHandler(GameNotAvailableException.class)
    public ResponseEntity<?> gameNotAvailableExceptionHandling(Exception exception, WebRequest request) {
        Error errorThrown = new Error (HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(errorThrown, errorThrown.getHttpStatus());
    }
    
    @ExceptionHandler(LoanPeriodTooLongException.class)
    public ResponseEntity<?> loanPeriodTooLongExceptionHandling(Exception exception, WebRequest request) {
        Error errorThrown = new Error (HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(errorThrown, errorThrown.getHttpStatus());
    }
    
    @ExceptionHandler(TooManyActiveLoansException.class)
    public ResponseEntity<?> tooManyActiveLoansExceptionHandling(Exception exception, WebRequest request) {
        Error errorThrown = new Error (HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(errorThrown, errorThrown.getHttpStatus());
    }
}