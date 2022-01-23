package com.cocktail_dakk.src.domain.keyword;

import com.sun.istack.NotNull;
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
@Table(name = "Keyword")
public class Keyword {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long keywordId;

    @Column(name = "keywordName")
    @NotNull
    private String keywordName;

    @OneToMany(mappedBy = "keyword")
    List<CocktailKeyword> cocktailKeywords=new ArrayList<>();

    @Builder
    public Keyword(String keywordName){
        this.keywordName=keywordName;
    }
}
