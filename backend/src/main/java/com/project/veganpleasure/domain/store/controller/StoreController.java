package com.project.veganpleasure.domain.store.controller;

import com.project.veganpleasure.domain.common.service.UploadFileService;
import com.project.veganpleasure.domain.store.dto.StoreDetailDto;
import com.project.veganpleasure.domain.store.dto.StoreDto;
import com.project.veganpleasure.domain.store.entity.District;
import com.project.veganpleasure.domain.store.entity.Store;
import com.project.veganpleasure.domain.store.repository.StoreRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categories",
                    value = "카테고리"
            ),
            @ApiImplicitParam(name = "vegetarianTypes",
                    value = "채식타입"
            ),
            @ApiImplicitParam(name = "categories",
                    value = "정렬 기준 ( starRating / likes )"
            )
    })
    public List<StoreDto> getStores(@RequestParam(value = "categories", required = false) String categories,
                                    @RequestParam(value = "vegetarianTypes", required = false) String vegetarianTypes,
                                    @RequestParam(value = "sorted", required = false) String cond){
        return storeRepository.findByCategoriesAndVegetarianTypes(categories, vegetarianTypes, cond)
                .stream()
                .map(StoreDto::new)
                .collect(Collectors.toList());
    }

    @ApiOperation("지역에 따른 맛집 조회")
    @GetMapping("/{district}")
    @ResponseStatus(HttpStatus.OK)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "district",
                    value = "지역",
                    paramType = "path",
                    required = true)
    })
    public List<StoreDto> getStoresByDistrict(@PathVariable("district") String district){
        return storeRepository.findByDistrict(findDistrict(district))
                .stream()
                .map(StoreDto::new)
                .collect(Collectors.toList());
    }

    @ApiOperation("맛집 상세 조회")
    @GetMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",
                    value = "지역",
                    paramType = "path",
                    required = true)
    })
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
