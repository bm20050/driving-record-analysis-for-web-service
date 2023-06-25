package com.jupiter.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class UploadList extends AuditingFields{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upload_id")
    private Long id;

    @CreatedDate
    private LocalDateTime requestDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "uploadList", cascade = CascadeType.ALL)
    private List<MyFileList> fileList = new ArrayList<>();

    // 양방향일때 연관관계 메서드 //
    public void setMember(Member member) {
        this.member = member;
        member.getUploadList().add(this);
    }

    public void addFileItem(MyFileList myFile){
        fileList.add(myFile);
        myFile.setUploadList(this);
    }

    //==생성메서드==//
    public static UploadList createUploadList(Member member, List<MyFileList> list){

        UploadList uploadList = new UploadList();

        uploadList.setMember(member);
//        uploadList.setRequestDate(LocalDateTime.now());

        for (MyFileList myFile : list)
            uploadList.addFileItem(myFile);

        return uploadList;
    }

}
