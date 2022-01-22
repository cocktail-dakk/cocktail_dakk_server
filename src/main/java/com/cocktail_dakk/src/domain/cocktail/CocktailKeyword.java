package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.keyword.Keyword;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class CocktailKeyword {

    public CocktailKeyword(CocktailInfo cocktailInfo, Keyword keyword) {
        this.keyword = keyword;
        this.cocktailInfo=cocktailInfo;
        cocktailInfo.getCocktailKeywords().add(this);
        keyword.getCocktailKeywords().add(this);
    }

    @Id @GeneratedValue
    private Long cocktailKeywordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keywordId")
    private Keyword keyword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cocktialInfoId")
    private CocktailInfo cocktailInfo;
}
