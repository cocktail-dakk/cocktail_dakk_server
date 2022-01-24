package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.Status;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "CocktailInfo")
public class CocktailInfo {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long cocktailInfoId;

    @NotNull
    private String englishName;

    @NotNull
    private String koreanName;

    @Lob
    @NotNull
    private String description;

    @Lob
    @Column(name = "mainImgUrl")
    @NotNull
    private String cocktailImageURL;

    @Lob
    @Column(name = "nukkiImgUrl")
    @NotNull
    private String cocktailBackgroundImageURL;
    @Lob
    @Column(name = "todayImgUrl")
    @NotNull
    private String recommendImageURL;

    @NotNull
    private Integer alcoholLevel;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @OneToMany(mappedBy = "cocktailInfo")
    private List<CocktailKeyword> cocktailKeywords=new ArrayList<>();

    @OneToMany(mappedBy = "cocktailInfo", fetch = FetchType.EAGER)
    private List<CocktailDrink> cocktailDrinks=new ArrayList<>();

    @OneToMany(mappedBy = "cocktailInfo")
    private List<CocktailMixingMethod> cocktailMixingMethods=new ArrayList<>();

    @Builder
    public CocktailInfo(String englishName, String koreanName, String description, String cocktailImageURL, String cocktailBackgroundImageURL, String recommendImageURL , Integer alcoholLevel, Status status){
        this.englishName=englishName;
        this.koreanName=koreanName;
        this.description=description;
        this.cocktailImageURL=cocktailImageURL;
        this.cocktailBackgroundImageURL=cocktailBackgroundImageURL;
        this.recommendImageURL=recommendImageURL;
        this.alcoholLevel=alcoholLevel;
        this.status=status;
    }
}
