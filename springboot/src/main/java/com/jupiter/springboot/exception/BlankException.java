package com.jupiter.springboot.exception;

public class BlankException extends RuntimeException{

    public BlankException() {
        super();
    }

    public BlankException(String message) {
        super(message);
    }

    public BlankException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlankException(Throwable cause) {
        super(cause);
    }

    protected BlankException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
