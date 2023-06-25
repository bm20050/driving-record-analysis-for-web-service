package com.jupiter.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyFileList extends AuditingFields{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;
    private String newName;
    private String originName;
    private String fileExtension;
    private long fileSize;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "upload_id")
    @Setter
    private UploadList uploadList;

    public MyFileList(String newName, String originName, String fileExtension, long fileSize) {
        this.newName = newName;
        this.originName = originName;
        this.fileExtension = fileExtension;
        this.fileSize = fileSize;
    }

    //==생성메서드==//
    public static MyFileList createMyFile(String newName, String originName, String fileExtension, long fileSize){
        return new MyFileList(newName, originName, fileExtension, fileSize);
    }

}
