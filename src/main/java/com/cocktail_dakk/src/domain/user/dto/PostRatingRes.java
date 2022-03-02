package com.cocktail_dakk.src.domain.user.dto;

import com.cocktail_dakk.src.domain.cocktail.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostRatingRes {
    private Long ratingId;
    private Long cocktailId;
    private Long userId;

    public PostRatingRes(Rating rating) {
        this.ratingId = rating.getRatingId();
        this.cocktailId = rating.getCocktailInfo().getCocktailInfoId();
        this.userId = rating.getUserInfo().getUserInfoId();
    }
}
