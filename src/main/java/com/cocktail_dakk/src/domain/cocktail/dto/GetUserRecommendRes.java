package com.cocktail_dakk.src.domain.cocktail.dto;

import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.keyword.dto.KeywordRes;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class GetUserRecommendRes {
    private Long cocktailInfoId;
    private String cocktailImageURL;

    public GetUserRecommendRes (CocktailInfo cocktailInfo){
        this.cocktailInfoId = cocktailInfo.getCocktailInfoId();
        this.cocktailImageURL = cocktailInfo.getCocktailImageURL();
    }
}
