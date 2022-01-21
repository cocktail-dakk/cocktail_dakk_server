package com.cocktail_dakk.src.domain.keyword;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.cocktail_dakk.src.domain.cocktail.CocktailKeyword;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Keyword {

    @Id @GeneratedValue
    private Long keywordId;

    private String keywordName;

    @OneToMany(mappedBy = "keyword" /*,cascade = CascadeType.ALL*/)
    List<CocktailKeyword> cocktailKeywords=new ArrayList<>();

    @Builder
    public Keyword(String keywordName){
        this.keywordName=keywordName;
    }
}