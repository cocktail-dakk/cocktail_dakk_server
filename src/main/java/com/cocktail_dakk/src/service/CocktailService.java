package com.cocktail_dakk.src.service;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.cocktail.*;
import com.cocktail_dakk.src.domain.cocktail.dto.*;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.domain.user.dto.PostRatingRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

import static com.cocktail_dakk.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CocktailService {
    private final CocktailInfoRepository cocktailInfoRepository;
    private final RatingRepository ratingRepository;


    //칵테일 상세페이지 띄우기
    public CocktailDetailsInfoRes getCocktailDetailsInfo(Long id) throws BaseException {
        try {
            CocktailInfo detailCocktail = cocktailInfoRepository.findByCocktailInfoId(id);
            return new CocktailDetailsInfoRes(detailCocktail);
        } catch (Exception e){
            throw new BaseException(RESPONSE_ERROR);
        }
    }

    //평점 확인
    @Transactional
    public PostRatingRes addPoint(UserInfo userInfo, CocktailInfo cocktailInfo, float starPoint) throws BaseException{
        if (ratingRepository.findByUserInfoAndCocktailInfo(userInfo, cocktailInfo).isPresent()){
            throw new BaseException(POST_EXISTS_RATING);
        }
        try {
            Rating rating = Rating.builder()
                    .cocktailInfo(cocktailInfo)
                    .userInfo(userInfo)
                    .rating(starPoint)
                    .build();
            Rating saveRating = ratingRepository.save(rating);

            return new PostRatingRes(saveRating);
        } catch (Exception e){
            throw new BaseException(RESPONSE_ERROR);
        }

    }

    public CocktailInfo getCocktailInfo(Long cocktailInfoId) throws BaseException {

        Optional<CocktailInfo> cocktailInfo = cocktailInfoRepository.findById(cocktailInfoId);

        if(cocktailInfo.isEmpty() || cocktailInfo.get().getStatus() == Status.INACTIVE ){
            throw  new BaseException(NOT_EXIST_USER);
        }

        return cocktailInfo.get();
    }

}