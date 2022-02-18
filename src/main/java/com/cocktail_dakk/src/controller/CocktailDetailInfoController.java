package com.cocktail_dakk.src.controller;


import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.BaseResponse;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.dto.CocktailDetailsInfoRes;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.domain.user.dto.PostRatingReq;
import com.cocktail_dakk.src.domain.user.dto.PostRatingRes;
import com.cocktail_dakk.src.service.CocktailService;
import com.cocktail_dakk.src.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.cocktail_dakk.config.BaseResponseStatus.STAR_OUT_OF_RANGE;

@RequiredArgsConstructor
@RestController
@RequestMapping("cocktaildakk/v1/cocktails")
public class CocktailDetailInfoController {

    private final CocktailService cocktailService;
    private final UserInfoService userInfoService;

    @GetMapping("/details")
    public BaseResponse<CocktailDetailsInfoRes> getCocktailInfo(@RequestParam Long id){
//        return cocktailService.getCocktailDetailsInfo(id);
        try {
            return new BaseResponse<>(cocktailService.getCocktailDetailsInfo(id));
        } catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

//    @PostMapping("/rating")
//    public BaseResponse<PostRatingRes> rateCocktail(@RequestBody PostRatingReq postRatingReq) throws BaseException{
//        if (postRatingReq.getStarPoint() > 5.0 || postRatingReq.getStarPoint() < 0.0 ){
//            return new BaseResponse<>(STAR_OUT_OF_RANGE);
//        }
//        try {
//            UserInfo userInfo = userInfoService.getUserInfo(postRatingReq.getDeviceNum());
//            CocktailInfo cocktailInfo = cocktailService.getCocktailInfo(postRatingReq.getCocktailInfoId());
//            return new BaseResponse<>(cocktailService.addPoint(userInfo, cocktailInfo,postRatingReq.getStarPoint()));
//        } catch (BaseException e){
//            return new BaseResponse<>(e.getStatus());
//        }
//    }

}
