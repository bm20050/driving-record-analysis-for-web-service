package com.jupiter.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Getter @Setter
public class MemberJoinDto {

    @NotBlank(message = "빈칸 허용 안함")
    private String userid;

    private String username;

    @NotBlank(message = "빈칸 허용 안함")
    private String password;

    @Email(message = "잘못된 이메일 형식")
    private String email;

}
