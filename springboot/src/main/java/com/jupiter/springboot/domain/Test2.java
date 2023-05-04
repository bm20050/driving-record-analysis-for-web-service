package com.jupiter.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
public class Test2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long seq;

    public String userName;

    public Integer age;

}
