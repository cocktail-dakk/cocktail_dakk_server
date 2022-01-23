package com.cocktail_dakk.src.domain.cocktail.dto;

import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.CocktailKeyword;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.List;

@Getter
@NoArgsConstructor
public class GetTodayCocktailInfoRes {
    private Long cocktailInfoId;
    private String englishName;
    private String koreanName;
    private List<CocktailKeyword> cocktailKeywords;
    private String recommendImageURL;

    public GetTodayCocktailInfoRes (CocktailInfo cocktailInfo){
        this.cocktailInfoId = cocktailInfo.getCocktailInfoId();
        this.englishName = cocktailInfo.getEnglishName();
        this.koreanName = cocktailInfo.getKoreanName();
        this.cocktailKeywords = cocktailInfo.getCocktailKeywords();
        this.recommendImageURL = cocktailInfo.getRecommendImageURL();
    }

}
