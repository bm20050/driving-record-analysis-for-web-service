package com.jupiter.springboot.exception;

public class DupUserException extends RuntimeException{

    public DupUserException() {
        super();
    }

    public DupUserException(String message) {
        super(message);
    }

    public DupUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public DupUserException(Throwable cause) {
        super(cause);
    }

    protected DupUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
