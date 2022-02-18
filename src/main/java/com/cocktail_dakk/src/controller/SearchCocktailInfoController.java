package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.BaseResponse;
import com.cocktail_dakk.src.domain.cocktail.dto.SearchCocktailInfoRes;
import com.cocktail_dakk.src.service.FilterCocktailInfoService;
import com.cocktail_dakk.src.service.SearchCocktailInfoService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "칵테일 검색",
            description = "<ul>" +
                    "<li> 검색창에서 칵테일을 검색하면 칵테일의 이름, 재료, 키워드에서 일치하는 값이 있는 칵테일들을 반환한다. 페이징은 page만 처리했으므로 size와 sorting은 제외한 pageable JSON 객체를 보내야 한다.</li>" +
                    "<li> 검색 요청 보내는 법 : pageable={\"page\" : 페이지번호}, inputStr=검색값</li>" +
                    "<li> 모든 칵테일 조회하는 법 : pageable={\"page\" : 페이지번호}, inputStr=비워두거나 띄워쓰기 같은 공백문자</li>" +
                    "</ul>")
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
    @Operation(summary = "칵테일 필터",
            description = "<ul>" +
                    "<li> 검색창에서 칵테일을 검색하면 칵테일의 이름, 재료, 키워드에서 일치하는 값이 있는 칵테일들을 반환한다. 페이징은 page만 처리했으므로 size와 sorting은 제외한 pageable JSON 객체를 보내야 한다.</li>" +
                    "<li> 필터 요청 보내는 법 : pageable={\"page\" : 페이지번호}, keywordName에 값 추가, minAlcoholLevel=최소 도수, maxAlcoholLevel=최대 도수, drinkName에 값 추가</li>" +
                    "<li> 필터 요청 보내는 법 : 필터 조건 추가 안하는 것은 입력 안하면 된다.</li>" +
                    "</ul>")
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