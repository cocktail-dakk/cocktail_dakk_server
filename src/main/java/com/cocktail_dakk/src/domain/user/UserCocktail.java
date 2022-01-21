package com.cocktail_dakk.src.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;

@Entity
@NoArgsConstructor
@Getter
public class UserCocktail {

    public UserCocktail(UserInfo userInfo, CocktailInfo cocktailInfo, Double rating, String review) {
        this.userInfo = userInfo;
        this.cocktailInfo = cocktailInfo;
        userInfo.getUserCocktails().add(this);
        this.rating = rating;
        this.review = review;
    }

    @Id
    @GeneratedValue
    private Long userCocktailId;

    private Double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInfoId")
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cocktailInfoId")
    private CocktailInfo cocktailInfo;

    @Lob
    private String review;
}
