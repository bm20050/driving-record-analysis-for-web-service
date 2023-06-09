package com.jupiter.springboot.controller;

import com.jupiter.springboot.dto.UploadUserDto;
import com.jupiter.springboot.service.UploadListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UploadListController {

    private final UploadListService uploadListService;

    @PostMapping("/api/uploadFiles")
    public ResponseEntity<Object> uploadFiles(
            @RequestPart(value = "userid") UploadUserDto userid,
            @RequestPart(value = "multipartFiles") MultipartFile[] multipartFiles) {

        Long fileListId;

        try{
            System.out.println(userid.getUserid());
            System.out.println(multipartFiles.toString());
            System.out.println("컨트롤러");
            fileListId = uploadListService.uploadFiles(userid.getUserid(), multipartFiles);

        } catch (Exception e) {
            System.out.println("에러?");
            return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok().body(uploadListService.transferUploadList(fileListId));
//        return ResponseEntity.ok().body(fileListId);
    }
}
