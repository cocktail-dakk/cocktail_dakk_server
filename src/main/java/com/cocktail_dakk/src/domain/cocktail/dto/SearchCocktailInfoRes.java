package com.cocktail_dakk.src.domain.cocktail.dto;

import com.cocktail_dakk.src.domain.cocktail.CocktailKeyword;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SearchCocktailInfoRes {
    private Long cocktailInfoId;
    private String englishName;
    private String koreanName;
    private List<CocktailKeyword> cocktailKeywords;
    private String cocktailBackgroundImageURL;
}
