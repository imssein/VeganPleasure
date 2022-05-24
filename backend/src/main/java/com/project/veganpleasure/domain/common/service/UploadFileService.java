package com.project.veganpleasure.domain.common.service;

import com.project.veganpleasure.domain.common.entity.UploadFile;
import com.project.veganpleasure.domain.common.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadFileService {
    @Value("${file.dir}")
    private String fileDir;
    private final UploadFileRepository uploadFileRepository;

    public String getFullPath(String filename){
        return fileDir + filename;
    }

    // 실제 서버에 저장되는 파일이름 ( uuid를 이용해서 유일성 보장 )
    public String createSavedFileName(String originalFilename){
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    // 확장자 추출
    public String extractExt(String originalFilename){
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

    // uploadfile table에 저장후 필요한 엔티티에 저장하는 방식으로
    public UploadFile saveFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String savedFileName = createSavedFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(savedFileName)));

        return uploadFileRepository.save(new UploadFile(originalFilename, savedFileName));
    }
}
