package com.luisskipthedishes.restaurant.exception;



import org.springframework.http.HttpStatus;

public class ApplicationResourceException extends ApplicationException{

    public ApplicationResourceException(String message, HttpStatus status) {
        super(message, status);
    }

    public ApplicationResourceException(String message, Throwable cause, HttpStatus status) {
        super(message, cause, status);
    }

    public ApplicationResourceException(Throwable cause, HttpStatus status) {
        super(cause, status);
    }
}
