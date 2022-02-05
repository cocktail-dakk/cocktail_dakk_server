package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.BaseResponse;
import com.cocktail_dakk.src.domain.cocktail.dto.SearchCocktailInfoRes;
import com.cocktail_dakk.src.service.SearchCocktailInfoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import static com.cocktail_dakk.config.BaseResponseStatus.REQUEST_ERROR;

@RestController
@RequestMapping("/search")
public class SearchCocktailInfoController {

    @Autowired
    SearchCocktailInfoService searchCocktailInfoService;

    @GetMapping(value = "/cocktail")
    @Operation(summary = "칵테일 검색",
            description = "<ul>" +
                    "<li> 검색창에서 칵테일을 검색하면 칵테일의 이름, 재료, 키워드에서 일치하는 값이 있는 칵테일들을 반환한다. 페이징은 page만 처리했으므로 size와 sorting은 제외한 pageable JSON 객체를 보내야 한다.</li>" +
                    "<li> 검색 요청 보내는 법 : pageable={\"page\" : 페이지번호}, inputStr=검색값</li>" +
                    "<li> 모든 칵테일 조회하는 법 : pageable={\"page\" : 페이지번호}, inputStr=비워두거나 띄워쓰기 같은 공백문자</li>" +
                    "</ul>")
    public BaseResponse<Slice<SearchCocktailInfoRes>> searchCocktailByInputStr(@PageableDefault(size = 10) Pageable pageable, @RequestParam("inputStr") String inputStr)throws BaseException {
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
    @Operation(summary = "칵테일 검색",
            description = "<ul>" +
                    "<li> 검색창에서 칵테일을 검색하면 칵테일의 이름, 재료, 키워드에서 일치하는 값이 있는 칵테일들을 반환한다. 페이징은 page만 처리했으므로 size와 sorting은 제외한 pageable JSON 객체를 보내야 한다.</li>" +
                    "<li> 검색 요청 보내는 법 : pageable={\"page\" : 페이지번호}, inputStr=검색값</li>" +
                    "<li> 모든 칵테일 조회하는 법 : pageable={\"page\" : 페이지번호}, inputStr=비워두거나 띄워쓰기 같은 공백문자</li>" +
                    "</ul>")
    public BaseResponse<Slice<SearchCocktailInfoRes>> searchCocktailByInputStrWithFilter(@PageableDefault(size = 10) Pageable pageable, @RequestParam("inputStr") String inputStr)throws BaseException {
        try {
            if(!pageable.getSort().isEmpty()){
                throw new BaseException(REQUEST_ERROR);
            }
            if (inputStr.isEmpty()||inputStr.isBlank()) {
                throw new BaseException(REQUEST_ERROR);
            }

            return new BaseResponse<>(searchCocktailInfoService.findByInputStrAll(pageable, inputStr));
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}