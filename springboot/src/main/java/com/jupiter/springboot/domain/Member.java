package com.jupiter.springboot.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(unique = true)
    private String username;

    private String nickname;

    private String email;

    private String password;

    private String roles;

    @OneToMany(mappedBy="member")
    private List<UploadList> uploadList = new ArrayList<>();

    public Member(String userid, String username, String email, String password, String roles) {
        this.username = userid;
        this.nickname = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    protected Member() {

    }
}
