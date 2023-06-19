package com.jupiter.springboot.exhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.BindingResult;

@Data
@AllArgsConstructor
public class ErrorResult {
    private String code;
    private String message;

    public ErrorResult makeErrorResponse(BindingResult bindingResult){
        String description = "";
        String detail = "";
        //에러가 있다면
        if(bindingResult.hasErrors()){
            //DTO에 설정한 meaasge값을 가져온다
            description = bindingResult.getFieldError().getField();

            //DTO에 유효성체크를 걸어놓은 어노테이션명을 가져온다.
            detail = bindingResult.getFieldError().getCode();
        }

        return new ErrorResult(description, detail);
    }
}
