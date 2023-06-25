package com.jupiter.springboot.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends AuditingFields{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(unique = true)
    private String userid;

    @Setter
    private String username;

    @Setter
    private String email;

    @Setter
    private String password;

    private String roles;

    @OneToMany(mappedBy="member")
    private List<UploadList> uploadList = new ArrayList<>();

    public Member(String userid, String username, String email, String password, String roles) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

}
