package com.jupiter.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter @Setter
public class MemberJoinDto {

    @NotBlank
    private String userid;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email(message = "WrongEmail")
    @NotBlank
    private String email;


}
