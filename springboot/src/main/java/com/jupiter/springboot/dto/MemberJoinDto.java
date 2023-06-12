package com.jupiter.springboot.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class MemberJoinDto {

    @NotBlank
    private String userid;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email
    private String email;


}
