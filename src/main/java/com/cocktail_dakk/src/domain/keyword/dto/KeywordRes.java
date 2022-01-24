package com.cocktail_dakk.src.domain.keyword.dto;

import com.cocktail_dakk.src.domain.cocktail.CocktailKeyword;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KeywordRes {
    private Long keywordId;
    private String keywordName;

    public KeywordRes(CocktailKeyword cocktailKeyword) {
        this.keywordId = cocktailKeyword.getKeyword().getKeywordId();
        this.keywordName = cocktailKeyword.getKeyword().getKeywordName();
    }
}
