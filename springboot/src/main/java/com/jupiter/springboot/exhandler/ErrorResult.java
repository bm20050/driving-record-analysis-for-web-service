package com.jupiter.springboot.exhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.BindingResult;

@Data
@AllArgsConstructor
public class ErrorResult {
    private String code;
    private String message;
}
