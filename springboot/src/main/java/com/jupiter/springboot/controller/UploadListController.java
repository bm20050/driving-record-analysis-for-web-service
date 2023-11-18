package com.jupiter.springboot.controller;

import com.jupiter.springboot.domain.UploadList;
import com.jupiter.springboot.dto.MemberLoginRespDto;
import com.jupiter.springboot.dto.UploadUserDto;
import com.jupiter.springboot.exception.NoUserException;
import com.jupiter.springboot.exception.WrongFileException;
import com.jupiter.springboot.service.UploadListService;
//import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UploadListController {

    private final UploadListService uploadListService;

//    @ApiOperation(value = "파일 업로드", notes = "업로드 한 파일 플라스크로 전달하여 데이터 전처리 결과 응답", response = UploadList.class)
    @PostMapping("/api/uploadFiles")
    public ResponseEntity<String> uploadFiles(
            @RequestPart(value = "userid") UploadUserDto userid,
            @RequestPart(value = "multipartFiles") MultipartFile[] multipartFiles, BindingResult bindingResult) throws WrongFileException {

        Long fileListId;

        try{
            fileListId = uploadListService.uploadFiles(userid.getUserid(), multipartFiles);
        } catch (Exception e) {
            throw new NoUserException("사용자 없음");
        }

        try{
            return ResponseEntity.ok().body(uploadListService.transferUploadList(fileListId));
        } catch (Exception e){
            throw new WrongFileException("파일업로드에러");
        }
    }
}
