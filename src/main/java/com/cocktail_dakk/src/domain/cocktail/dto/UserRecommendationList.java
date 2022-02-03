package com.cocktail_dakk.src.domain.cocktail.dto;

import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UserRecommendationList {
    private Long cocktailInfoId;
    private String cocktailImageURL;

    public UserRecommendationList(CocktailInfo cocktailInfo){
        this.cocktailInfoId = cocktailInfo.getCocktailInfoId();
        this.cocktailImageURL = cocktailInfo.getCocktailImageURL();
    }

}
