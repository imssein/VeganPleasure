package com.project.veganpleasure.domain.common.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class TestController {

    @PostMapping("/file")
    public String getFile(MultipartFile multipartFile) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(multipartFile.getName() + "\n");
        sb.append(multipartFile.getOriginalFilename() + "\n");
        sb.append(multipartFile.getContentType() + "\n");
        sb.append(multipartFile.isEmpty() + "\n");
        sb.append(multipartFile.getSize() + "\n");
        sb.append(multipartFile.getBytes().toString() + "\n");
        sb.append(multipartFile.getInputStream() + "\n");

        return sb.toString();
    }
}
