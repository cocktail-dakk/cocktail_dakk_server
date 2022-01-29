package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.BaseResponse;
import com.cocktail_dakk.src.domain.cocktail.dto.GetTodayCocktailInfoRes;
import com.cocktail_dakk.src.domain.cocktail.dto.GetUserRecommendRes;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.service.CocktailService;
import com.cocktail_dakk.src.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class RecommendCocktailController {

    private final CocktailService cocktailService;
    private final UserInfoService userInfoService;

    @GetMapping("/today")
    public BaseResponse<List<GetTodayCocktailInfoRes>> getTodayCocktailInfoRes(){
        return new BaseResponse<>(cocktailService.getTodayCocktail());
    }

    @GetMapping("/user/{deviceNum}")
    public BaseResponse<List<GetUserRecommendRes>> getUserCocktailRes(@PathVariable("deviceNum") String deviceNum){
        try {
            UserInfo userInfo = userInfoService.getUserInfo(deviceNum);
            return new BaseResponse<>(cocktailService.getUserRecommendCocktail(userInfo));
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
