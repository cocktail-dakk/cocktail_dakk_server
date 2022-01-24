package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.src.domain.cocktail.dto.SearchCocktailInfoRes;
import com.cocktail_dakk.src.service.SearchCocktailInfoService;
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

    @GetMapping("/cocktail")
    public Page<SearchCocktailInfoRes> searchCocktailByInputStr(@PageableDefault(size = 10) Pageable pageable, @RequestParam("inputStr") String inputStr){
        if(inputStr.isEmpty()){
            return searchCocktailInfoService.findAll(pageable);
        }else {
            return searchCocktailInfoService.findByInputStrAll(pageable, inputStr);
        }
    }

}