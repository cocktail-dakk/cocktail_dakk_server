package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.BaseResponse;
import com.cocktail_dakk.src.domain.cocktail.dto.GetRecommendationListRes;
import com.cocktail_dakk.src.domain.cocktail.dto.GetTodayCocktailInfoRes;
import com.cocktail_dakk.src.domain.cocktail.dto.GetUserRecommendationRes;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.service.UserInfoService;
import com.cocktail_dakk.src.service.recommend.KeywordCocktailService;
import com.cocktail_dakk.src.service.recommend.TodayCocktailService;
import com.cocktail_dakk.src.service.recommend.UserCocktailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cocktaildakk/v1/recommend")
@RequiredArgsConstructor
@Slf4j
public class RecommendCocktailController {

    private final TodayCocktailService todayCocktailService;
    private final KeywordCocktailService cocktailService;
    private final UserCocktailService userCocktailService;
    private final UserInfoService userInfoService;

    @GetMapping("/today")
    public BaseResponse<List<GetTodayCocktailInfoRes>> getTodayCocktailInfoRes() throws BaseException {
        try {
            return new BaseResponse<>(todayCocktailService.getTodayCocktail());
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/user/")
    public BaseResponse<GetUserRecommendationRes> getUserCocktailRes(){
        try {
            UserInfo userInfo = userInfoService.getUserInfoWithKeywordAndDrink();
            return new BaseResponse<>(userCocktailService.getUserRecommendCocktail(userInfo));
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/keyword/")
    public BaseResponse<List<GetRecommendationListRes>> getKeywordCocktailRes(){
        List<GetRecommendationListRes> recommendationRes = new ArrayList<>();
        try {
            UserInfo userInfo = userInfoService.getUserInfoWithKeywordAndDrink();
            recommendationRes.add(cocktailService.getKeywordRecommendation(userInfo));
            log.info("=== 키워드 ===");
            recommendationRes.add(cocktailService.getDrinkRecommendation(userInfo));
            return new BaseResponse<>(recommendationRes);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
