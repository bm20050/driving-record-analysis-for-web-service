package com.jupiter.springboot.exception;

public class WrongFileException extends Exception{

    public WrongFileException() {
        super();
    }

    public WrongFileException(String message) {
        super(message);
    }

    public WrongFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongFileException(Throwable cause) {
        super(cause);
    }

    protected WrongFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
