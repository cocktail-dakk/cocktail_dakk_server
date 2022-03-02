package com.cocktail_dakk.src.domain.cocktail.dto;

import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.CocktailKeyword;
import com.cocktail_dakk.src.domain.cocktail.CocktailToday;
import com.cocktail_dakk.src.domain.keyword.Keyword;
import com.cocktail_dakk.src.domain.keyword.dto.KeywordRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class GetTodayCocktailInfoRes {
    private Long cocktailInfoId;
    private String englishName;
    private String koreanName;
    private List<KeywordRes> cocktailKeywords;
    private String recommendImageURL;

    public GetTodayCocktailInfoRes (CocktailInfo cocktailInfo){
        this.cocktailInfoId = cocktailInfo.getCocktailInfoId();
        this.englishName = cocktailInfo.getEnglishName();
        this.koreanName = cocktailInfo.getKoreanName();
        this.cocktailKeywords = cocktailInfo.getCocktailKeywords()
                .stream()
                .map(KeywordRes::new)
                .collect(Collectors.toList());
        this.recommendImageURL = cocktailInfo.getRecommendImageURL();
    }
}
