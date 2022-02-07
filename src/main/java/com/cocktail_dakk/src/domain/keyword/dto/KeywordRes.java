package com.cocktail_dakk.src.domain.keyword.dto;


import com.cocktail_dakk.src.domain.cocktail.CocktailKeyword;
import com.cocktail_dakk.src.domain.keyword.Keyword;
import com.cocktail_dakk.src.domain.user.UserKeyword;
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

    public KeywordRes(Keyword keyword) {
        this.keywordId = keyword.getKeywordId();
        this.keywordName = keyword.getKeywordName();
    }

    public KeywordRes(UserKeyword userKeyword) {
        this.keywordId = userKeyword.getKeyword().getKeywordId();
        this.keywordName = userKeyword.getKeyword().getKeywordName();
    }
}

