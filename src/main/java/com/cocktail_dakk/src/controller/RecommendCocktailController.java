package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.config.BaseResponse;
import com.cocktail_dakk.src.domain.cocktail.dto.GetTodayCocktailInfoRes;
import com.cocktail_dakk.src.service.CocktailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class RecommendCocktailController {

    private final CocktailService cocktailService;

    @GetMapping("/today")
    public BaseResponse<List<GetTodayCocktailInfoRes>> getTodayCocktailInfoRes(){
        return new BaseResponse<>(cocktailService.getTodayCocktail());
    }


}
