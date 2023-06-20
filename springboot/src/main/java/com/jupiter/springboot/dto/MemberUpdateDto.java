package com.jupiter.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Getter @Setter
public class MemberUpdateDto {

    @NotBlank(message = "빈칸 허용 안함")
    @ReadOnlyProperty
    private String userid;

    @NotBlank(message = "빈칸 허용 안함")
    private String username;

    @NotBlank(message = "빈칸 허용 안함")
    private String password;

    @Email(message = "잘못된 이메일 형식")
    @NotBlank(message = "빈칸 허용 안함")
    private String email;

}
