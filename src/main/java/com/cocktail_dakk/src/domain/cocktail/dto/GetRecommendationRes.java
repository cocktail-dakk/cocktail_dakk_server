package com.cocktail_dakk.src.domain.cocktail.dto;

import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.keyword.dto.KeywordRes;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class GetRecommendationRes {
    private Long cocktailInfoId;
    private String englishName;
    private String koreanName;
    private List<KeywordRes> cocktailKeywords;
    private String smallNukkiImageURL;
    private BigDecimal ratingAvg;

    public GetRecommendationRes(CocktailInfo cocktailInfo){
        this.cocktailInfoId = cocktailInfo.getCocktailInfoId();
        this.englishName = cocktailInfo.getEnglishName();
        this.koreanName = cocktailInfo.getKoreanName();
        this.cocktailKeywords = cocktailInfo.getCocktailKeywords()
                .stream()
                .map(KeywordRes::new)
                .collect(Collectors.toList());
        this.smallNukkiImageURL = cocktailInfo.getSmallNukkiImageURL();
        this.ratingAvg = cocktailInfo.getRatingAvg();
    }

}
