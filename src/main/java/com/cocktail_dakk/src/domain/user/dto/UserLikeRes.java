package com.cocktail_dakk.src.domain.user.dto;

import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.CocktailKeyword;
import com.cocktail_dakk.src.domain.keyword.Keyword;
import com.cocktail_dakk.src.domain.keyword.dto.KeywordRes;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class UserLikeRes { //한국어이름 영어이름 태그, 작누 큰누
    private Long cocktailInfoId;
    private String englishName;
    private String koreanName;
    private String smallNukkiImgUrl;
    private String nukkiImgUrl;
    private List<KeywordRes> cocktailKeyword;

    public UserLikeRes(CocktailInfo cocktailInfo) {
        this.cocktailInfoId = cocktailInfo.getCocktailInfoId();
        this.englishName = cocktailInfo.getEnglishName();
        this.koreanName = cocktailInfo.getKoreanName();
        this.nukkiImgUrl = cocktailInfo.getCocktailBackgroundImageURL();
        this.smallNukkiImgUrl = cocktailInfo.getSmallNukkiImageURL();
        this.cocktailKeyword = cocktailInfo.getCocktailKeywords()
                .stream().map(KeywordRes::new)
                .collect(Collectors.toList());
    }
}
