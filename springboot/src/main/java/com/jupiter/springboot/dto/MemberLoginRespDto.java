package com.jupiter.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class MemberLoginRespDto {

    private String userid;
    private String username;
    private String email;

}
