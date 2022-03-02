package com.cocktail_dakk.src.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "UserCocktail")
public class UserCocktail {

    @Builder
    public UserCocktail(UserInfo userInfo, CocktailInfo cocktailInfo) {
        this.userInfo = userInfo;
        this.cocktailInfo = cocktailInfo;
        userInfo.getUserCocktails().add(this);
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userCocktailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInfoId")
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cocktailInfoId")
    private CocktailInfo cocktailInfo;
}
