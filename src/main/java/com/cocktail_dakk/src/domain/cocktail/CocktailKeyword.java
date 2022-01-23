package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.keyword.Keyword;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "CocktailKeyword")
public class CocktailKeyword {

    public CocktailKeyword(CocktailInfo cocktailInfo, Keyword keyword) {
        this.keyword = keyword;
        this.cocktailInfo=cocktailInfo;
        cocktailInfo.getCocktailKeywords().add(this);
        keyword.getCocktailKeywords().add(this);
    }

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long cocktailKeywordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keywordId")
    private Keyword keyword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cocktailInfoId")
    private CocktailInfo cocktailInfo;
}
