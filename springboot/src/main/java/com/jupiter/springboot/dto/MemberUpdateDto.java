package com.jupiter.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter @Setter
public class MemberUpdateDto {

    @NotBlank @ReadOnlyProperty
    private String userid;

    private String username;

    @NotBlank
    private String password;

    private String email;

}
