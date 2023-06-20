package com.jupiter.springboot.service;

import com.jupiter.springboot.domain.Member;
import com.jupiter.springboot.domain.MyFileList;
import com.jupiter.springboot.domain.UploadList;
import com.jupiter.springboot.persistence.MemberRepository;
import com.jupiter.springboot.persistence.UploadListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UploadListService {

    private final UploadListRepository uploadListRepository;
    private final MemberRepository memberRepository;

    public Long uploadFiles(String userid, MultipartFile[] multipartFiles) throws IOException {

        String UPLOAD_PATH = "D:\\files";
        List<MyFileList> fileList = new ArrayList<>();
        Member member = memberRepository.findByUserid(userid).get();

        for(MultipartFile file : multipartFiles){

            String originName = file.getOriginalFilename(); // ex) 파일.jpg
            String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // 확장자
            originName = originName.substring(0, originName.lastIndexOf(".")); // 파일이름

            // 현재 날짜와 랜덤 정수값으로 새로운 파일명 만들기
            String newName = (new Date().getTime()) + "" + (new Random().ints(1000, 9999).findAny().getAsInt());

            long fileSize = file.getSize(); // 파일 사이즈

            File fileSave = new File(UPLOAD_PATH, newName + "." + fileExtension); // ex) fileId.jpg
            if (!fileSave.exists()) { // 폴더가 없을 경우 폴더 만들기
                fileSave.mkdirs();
            }

            file.transferTo(fileSave); // fileSave의 형태로 파일 저장

            MyFileList fileItem = MyFileList.createMyFile(newName, originName, fileExtension, fileSize);
            fileList.add(fileItem);

        }

        UploadList uploadList = UploadList.createUploadList(member, fileList);
        uploadListRepository.save(uploadList);

        return uploadList.getId();
    }

    public String transferUploadList(Long upLoadListId) {

        List<MyFileList> fileList = uploadListRepository.findById(upLoadListId).get().getFileList();
        List<String> list = fileList.stream().map(MyFileList::getNewName).toList();

        Map<String, List<String>> map = new HashMap<>();
        map.put("data", list);

        String flaskUrl = "http://localhost:5000/file_processing";

        RestTemplate restTemplate = new RestTemplate();
        System.out.println("지점1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        System.out.println("지점2");

        HttpEntity<Map<String, List<String>>> request = new HttpEntity<>(map, headers);
        System.out.println("지점3");
        ResponseEntity<String> response = restTemplate.postForEntity(flaskUrl, request, String.class);
        System.out.println("지점4");
        System.out.println(response);

        return response.getBody();
    }
}
