package com.project.veganpleasure.domain.store.controller;

import com.project.veganpleasure.domain.common.service.UploadFileService;
import com.project.veganpleasure.domain.store.dto.StoreDetailDto;
import com.project.veganpleasure.domain.store.dto.StoreDto;
import com.project.veganpleasure.domain.store.entity.District;
import com.project.veganpleasure.domain.store.repository.StoreRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("v1/api/stores")
public class StoreController {
    private final StoreRepository storeRepository;
    private final UploadFileService uploadFileService;

    @ApiOperation("전체 맛집 조회")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StoreDto> getStores(){
        return storeRepository.findAllFetch()
                .stream()
                .map(StoreDto::new)
                .collect(Collectors.toList());
    }

    @ApiOperation("지역에 따른 맛집 조회")
    @GetMapping("/{district}")
    @ResponseStatus(HttpStatus.OK)
    public List<StoreDto> getStoresByDistrict(@PathVariable("district") String district){
        return storeRepository.findAllByDistrict(findDistrict(district))
                .stream()
                .map(StoreDto::new)
                .collect(Collectors.toList());
    }

    @ApiOperation("맛집 상세 조회")
    @GetMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<StoreDetailDto> getStore(@PathVariable("id") Long id){
        List<StoreDetailDto> list = new ArrayList<>();
        list.add(new StoreDetailDto(storeRepository.findByIdFetch(id)));
        return list;
    }

    @ApiOperation("맛집 이미지 출력")
    @GetMapping("/images/{filename}")
    @ResponseStatus(HttpStatus.OK)
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + uploadFileService.getFullPath(filename));
    }

    private District findDistrict(String district) {
        District dist = null;
        for(District d : District.values()){
            if(d.getDistrictValue().equals(district)){
                dist = d;
                break;
            }
        }
        return dist;
    }
}
