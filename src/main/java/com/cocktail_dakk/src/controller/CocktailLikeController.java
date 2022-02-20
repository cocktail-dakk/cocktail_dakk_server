package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.BaseResponse;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.service.LikeService;
import com.cocktail_dakk.src.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cocktaildakk/v1/cocktails")
@RequiredArgsConstructor
public class CocktailLikeController {
    private final LikeService likeService;
    private final UserInfoService userInfoService;

    @PostMapping("/{cocktailId}/like")
    public BaseResponse<String> addLike (@PathVariable Long cocktailId){
        try {
            UserInfo userInfo = userInfoService.getUserInfo();
            likeService.addLike(userInfo, cocktailId);
            String result = "좋아요";
            return new BaseResponse<>(result);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @DeleteMapping("{cocktailId}/like")
    public BaseResponse<String> deleteLike (@PathVariable Long cocktailId){
        try {
            UserInfo userInfo = userInfoService.getUserInfo();
            likeService.deleteLike(userInfo, cocktailId);
            String result = "좋아요 취소";
            return new BaseResponse<>(result);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
