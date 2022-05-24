package com.project.veganpleasure.global.config;

import com.google.gson.Gson;
import com.project.veganpleasure.domain.common.entity.UploadFile;
import com.project.veganpleasure.domain.common.service.UploadFileService;
import com.project.veganpleasure.domain.member.entity.Member;
import com.project.veganpleasure.domain.member.repository.MemberRepository;
import com.project.veganpleasure.domain.review.entity.Review;
import com.project.veganpleasure.domain.review.repository.ReviewRepository;
import com.project.veganpleasure.domain.store.entity.District;
import com.project.veganpleasure.domain.store.entity.Store;
import com.project.veganpleasure.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() throws IOException, ParseException {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final StoreRepository storeRepository;
        private final ReviewRepository reviewRepository;
        private final MemberRepository memberRepository;
        private final UploadFileService uploadFileService;
        private final BCryptPasswordEncoder passwordEncoder;

        public void dbInit() throws IOException, ParseException {
            ClassPathResource resource = new ClassPathResource("data/data.json");
            Path path = Paths.get(resource.getURI());
            System.out.println("path = " + path);
            JSONArray jsonList = (JSONArray) new JSONParser().parse(new FileReader(path.toString()));

            ClassPathResource resource1 = new ClassPathResource("image/ex_store_image.png");
            Path path1 = Paths.get(resource1.getURI());
            File file = new File(path1.toString());
            FileItem fileItem = new DiskFileItem("originFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());

            FileInputStream input = new FileInputStream(file);
            OutputStream output = fileItem.getOutputStream();
            IOUtils.copy(input, output);

            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);

            UploadFile uploadFile = uploadFileService.saveFile(multipartFile);;

            for (Object o : jsonList) {
                Store store = new Gson().fromJson(o.toString(), Store.class);
                JSONObject jsonObject = (JSONObject) o;
                store.setVegetarianTypes(jsonObject.get("vegetarianType").toString());
                store.setDistrict(District.valueOf(jsonObject.get("district").toString()));
                store.setUploadFile(uploadFile);
                if (store.getMenu().length() > 255) {
                    store.setMenu(store.getMenu().substring(0, 255));
                }
                storeRepository.save(store);
            }
            Member member1 = Member.builder()
                    .email("yanglet@naver.com")
                    .password(passwordEncoder.encode("pw"))
                    .name("양충욱")
                    .nickname("양글렛")
                    .role("user")
                    .vegetarianTypes("비건")
                    .build();

            memberRepository.save(member1);

            Review review = Review.of("컨텐트", 3L, storeRepository.findByName("8빵"), member1, uploadFile);
            reviewRepository.save(review);
        }
    }
}
