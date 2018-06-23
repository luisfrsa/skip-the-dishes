package com.luisskipthedishes.restaurant.exception;



import org.springframework.http.HttpStatus;

public class ApplicationServiceException extends ApplicationException{
    public ApplicationServiceException(String message, HttpStatus status) {
        super(message, status);
    }

    public ApplicationServiceException(String message, Throwable cause, HttpStatus status) {
        super(message, cause, status);
    }

    public ApplicationServiceException(Throwable cause, HttpStatus status) {
        super(cause, status);
    }
}
