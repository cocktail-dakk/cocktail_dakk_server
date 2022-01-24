package com.cocktail_dakk.src.domain.cocktail.dto;

import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.CocktailKeyword;
import com.cocktail_dakk.src.domain.keyword.dto.KeywordRes;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class SearchCocktailInfoRes {
    private Long cocktailInfoId;
    private String englishName;
    private String koreanName;
    private List<KeywordRes> keywords;
    private String cocktailBackgroundImageURL;
    private BigDecimal rating;

    public SearchCocktailInfoRes(CocktailInfo cocktailInfo/*, BigDecimal rating*/) {
        this.cocktailInfoId = cocktailInfo.getCocktailInfoId();
        this.englishName = cocktailInfo.getEnglishName();
        this.koreanName = cocktailInfo.getKoreanName();
        this.keywords = cocktailInfo.getCocktailKeywords()
                .stream()
                .map(KeywordRes::new)
                .collect(Collectors.toList());
        this.cocktailBackgroundImageURL = cocktailInfo.getCocktailBackgroundImageURL();
//        this.rating = rating;
    }
}
