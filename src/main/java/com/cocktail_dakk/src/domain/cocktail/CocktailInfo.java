package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class CocktailInfo {

    @Id @GeneratedValue
    private Long cocktailInfoId;

    private String englishName;

    private String koreanName;

    @Lob
    private String description;

    @Lob
    private String cocktailImageURL;

    @Lob
    private String cocktailBackgroundImageURL;
    @Lob
    private String recommendImageURL;
    private Status status;

    @OneToMany(mappedBy = "cocktailInfo")
    private List<CocktailKeyword> cocktailKeywords=new ArrayList<>();

    @OneToMany(mappedBy = "cocktailInfo", fetch = FetchType.EAGER)
    private List<CocktailDrink> cocktailDrinks=new ArrayList<>();

    @OneToMany(mappedBy = "cocktailInfo")
    private List<CocktailIngredient> cocktailIngredients=new ArrayList<>();

    @OneToMany(mappedBy = "cocktailInfo")
    private List<CocktailMixingMethod> cocktailMixingMethods=new ArrayList<>();

    @Builder
    public CocktailInfo(String englishName, String koreanName, String description, String cocktailImageURL, String cocktailBackgroundImageURL, String recommendImageURL){
        this.englishName=englishName;
        this.koreanName=koreanName;
        this.description=description;
        this.cocktailImageURL=cocktailImageURL;
        this.cocktailBackgroundImageURL=cocktailBackgroundImageURL;
        this.recommendImageURL=recommendImageURL;
    }
}
