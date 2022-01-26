package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.src.domain.cocktail.dto.SearchCocktailInfoRes;
import com.cocktail_dakk.src.service.SearchCocktailInfoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchCocktailInfoController {

    @Autowired
    SearchCocktailInfoService searchCocktailInfoService;

    @GetMapping(value = "/cocktail")
    @Operation(summary = "칵테일 검색", description = "검색창에서 칵테일을 검색하면 칵테일의 이름, 재료, 키워드에서 일치하는 값이 있는 칵테일들을 반환한다.")
    public Page<SearchCocktailInfoRes> searchCocktailByInputStr(@PageableDefault(size = 10) Pageable pageable, @RequestParam("inputStr") String inputStr){
        if(inputStr.isEmpty()){
            return searchCocktailInfoService.findAll(pageable);
        }else {
            return searchCocktailInfoService.findByInputStrAll(pageable, inputStr);
        }
    }

}