package com.jupiter.springboot.exhandler;

import com.jupiter.springboot.exception.DupUserException;
import com.jupiter.springboot.exception.NoUserException;
import com.jupiter.springboot.exception.WrongPasswordException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    ErrorResult er;

    @ExceptionHandler
    public ResponseEntity<ErrorResult> noUserExHandler(NoUserException e) {
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("NoUser", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> wrongPwExHandler(WrongPasswordException e) {
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("WrongPassword", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> dupUserExHandler(DupUserException e) {
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("DupUser", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResult> validException(ConstraintViolationException e){
        log.error("[exceptionHandler] ex [getMessage={}]", e.getMessage());
        ErrorResult errorResult = new ErrorResult("field", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> validException(MethodArgumentNotValidException e){
        log.error("[exceptionHandler] ex [getMessage={}]", e.getMessage());
        ErrorResult errorResult = new ErrorResult(e.getFieldError().getField(), e.getFieldError().getDefaultMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

}
