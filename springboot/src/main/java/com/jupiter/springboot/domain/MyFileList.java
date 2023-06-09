package com.jupiter.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class MyFileList {

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
    private UploadList uploadList;

    //==생성메서드==//
    public static MyFileList createMyFile(String newName, String originName, String fileExtension, long fileSize){

        MyFileList myFile = new MyFileList();

        myFile.setNewName(newName);
        myFile.setOriginName(originName);
        myFile.setFileExtension(fileExtension);
        myFile.setFileSize(fileSize);

        return myFile;
    }

}
