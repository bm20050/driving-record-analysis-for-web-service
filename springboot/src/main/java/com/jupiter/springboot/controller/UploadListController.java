package com.jupiter.springboot.controller;

import com.jupiter.springboot.domain.UploadList;
import com.jupiter.springboot.dto.MemberLoginRespDto;
import com.jupiter.springboot.dto.UploadUserDto;
import com.jupiter.springboot.service.UploadListService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "파일 업로드", notes = "업로드 한 파일 플라스크로 전달하여 데이터 전처리 결과 응답", response = UploadList.class)
    @PostMapping("/api/uploadFiles")
    public ResponseEntity<Object> uploadFiles(
            @RequestPart(value = "userid") UploadUserDto userid,
            @RequestPart(value = "multipartFiles") MultipartFile[] multipartFiles) {

        Long fileListId;

        try{
            fileListId = uploadListService.uploadFiles(userid.getUserid(), multipartFiles);
        } catch (Exception e) {
            return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok().body(uploadListService.transferUploadList(fileListId));
    }
}
