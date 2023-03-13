package com.ccsw.tutorial.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author caliagaq
 *
 */


public class Error {
    private HttpStatus httpStatus;
    private String message;
    
    
    public Error(HttpStatus httpStatus2, String message2) {
        this.httpStatus = httpStatus2;
        this.message = message2;
    }

    /**
     * @param httpStatus new value of {@link #getHttpStatus}.
     */
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
    
    /**
     * @param message new value of {@link #getMessage}.
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * @return the httpStatus
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    
    @Override
    public String toString() {
        return "Error [httpStatus=" + httpStatus + ", message=" + message + "]";
    }
    
    
}
