package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.BaseResponse;
import com.cocktail_dakk.src.domain.cocktail.dto.GetRecommendationListRes;
import com.cocktail_dakk.src.domain.cocktail.dto.GetTodayCocktailInfoRes;
import com.cocktail_dakk.src.domain.cocktail.dto.GetUserRecommendRes;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.service.recommend.KeywordCocktailService;
import com.cocktail_dakk.src.service.UserInfoService;
import com.cocktail_dakk.src.service.recommend.TodayCocktailService;
import com.cocktail_dakk.src.service.recommend.UserCocktailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class RecommendCocktailController {

    private final TodayCocktailService todayCocktailService;
    private final KeywordCocktailService cocktailService;
    private final UserCocktailService userCocktailService;
    private final UserInfoService userInfoService;

    @GetMapping("/today")
    public BaseResponse<List<GetTodayCocktailInfoRes>> getTodayCocktailInfoRes(){
        return new BaseResponse<>(todayCocktailService.getTodayCocktail());
    }

    @GetMapping("/user/{deviceNum}")
    public BaseResponse<List<GetUserRecommendRes>> getUserCocktailRes(@PathVariable("deviceNum") String deviceNum){
        try {
            UserInfo userInfo = userInfoService.getUserInfo(deviceNum);
            return new BaseResponse<>(userCocktailService.getUserRecommendCocktail(userInfo));
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/keyword/{deviceNum}")
    public BaseResponse<List<GetRecommendationListRes>> getKeywordCocktailRes(@PathVariable("deviceNum") String deviceNum){
        List<GetRecommendationListRes> recommendationRes = new ArrayList<>();
        try {
            UserInfo userInfo = userInfoService.getUserInfo(deviceNum);
            recommendationRes.add(cocktailService.getKeywordRecommendation(userInfo));
            recommendationRes.add(cocktailService.getDrinkRecommendation(userInfo));
            return new BaseResponse<>(recommendationRes);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
