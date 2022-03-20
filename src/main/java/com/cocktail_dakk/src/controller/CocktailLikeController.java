package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.BaseResponse;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.domain.user.dto.UserLikeRes;
import com.cocktail_dakk.src.service.LikeService;
import com.cocktail_dakk.src.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cocktaildakk/v1/cocktails/like")
@RequiredArgsConstructor
public class CocktailLikeController {
    private final LikeService likeService;
    private final UserInfoService userInfoService;
    private static final String LIKE = "즐겨찾기";
    private static final String UNLIKE = "즐겨찾기 취소";

    @PostMapping("/{cocktailId}")
    public BaseResponse<String> addLike (@PathVariable Long cocktailId){
        try {
            UserInfo userInfo = userInfoService.getUserInfo();
            likeService.addLike(userInfo, cocktailId);
            return new BaseResponse<>(LIKE);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @DeleteMapping("/{cocktailId}")
    public BaseResponse<String> deleteLike (@PathVariable Long cocktailId){
        try {
            UserInfo userInfo = userInfoService.getUserInfo();
            likeService.deleteLike(userInfo, cocktailId);
            return new BaseResponse<>(UNLIKE);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping
    public BaseResponse<List<UserLikeRes>> getLikes (){
        try {
            UserInfo userInfo = userInfoService.getUserInfoWithKeywordAndDrink();
            return new BaseResponse<>(likeService.getUserLikes(userInfo));
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
