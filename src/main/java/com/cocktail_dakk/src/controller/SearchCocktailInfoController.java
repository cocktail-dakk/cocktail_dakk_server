package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.BaseResponse;
import com.cocktail_dakk.src.domain.cocktail.dto.SearchCocktailInfoRes;
import com.cocktail_dakk.src.service.FilterCocktailInfoService;
import com.cocktail_dakk.src.service.SearchCocktailInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cocktail_dakk.config.BaseResponseStatus.REQUEST_ERROR;

@RestController
@RequestMapping("cocktaildakk/v1/search")
public class SearchCocktailInfoController {

    @Autowired
    SearchCocktailInfoService searchCocktailInfoService;

    @Autowired
    FilterCocktailInfoService filterCocktailInfoService;

    @GetMapping(value = "/cocktail")
    public BaseResponse<Slice<SearchCocktailInfoRes>> searchCocktailByInputStr(@PageableDefault(size = 10) Pageable pageable, @RequestParam(value = "inputStr", defaultValue = "") String inputStr)throws BaseException {
        try {
            if(!pageable.getSort().isEmpty()){
                throw new BaseException(REQUEST_ERROR);
            }

            if (inputStr.isEmpty()||inputStr.isBlank()) {
                return new BaseResponse<>(searchCocktailInfoService.findAll(pageable));
            } else {
                return new BaseResponse<>(searchCocktailInfoService.findByInputStrAll(pageable, inputStr));
            }
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping(value = "/cocktail/filter")
    public BaseResponse<Slice<SearchCocktailInfoRes>> searchCocktailByFilter(@PageableDefault(size = 10) Pageable pageable, @RequestParam(value = "keywordName", defaultValue = "") List<String> keywordName, @RequestParam(value = "minAlcoholLevel", defaultValue = "0") Integer minAlcoholLevel, @RequestParam(value = "maxAlcoholLevel", defaultValue = "100") Integer maxAlcoholLevel, @RequestParam(value = "drinkName", defaultValue = "")List<String> drinkName)throws BaseException {
        try {
            if(!pageable.getSort().isEmpty()){
                throw new BaseException(REQUEST_ERROR);
            }
            if(minAlcoholLevel>maxAlcoholLevel){
                throw new BaseException(REQUEST_ERROR);
            }

            if(keywordName.isEmpty()){
                filterCocktailInfoService.findAllKeyword().forEach(keyword -> keywordName.add(keyword.getKeywordName()));
            }

            if(drinkName.isEmpty()){
                filterCocktailInfoService.findAllDrink().forEach(drink -> drinkName.add(drink.getDrinkName()));
            }

            return new BaseResponse<>(filterCocktailInfoService.findByFilterAll(pageable, keywordName, minAlcoholLevel, maxAlcoholLevel, drinkName));
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}