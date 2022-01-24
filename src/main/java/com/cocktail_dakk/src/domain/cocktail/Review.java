package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.user.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Review {

    public Review(CocktailInfo cocktailInfo, UserInfo userInfo) {
        this.cocktailInfo = cocktailInfo;
        this.userInfo = userInfo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cocktailInfoId")
    private CocktailInfo cocktailInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInfoId")
    private UserInfo userInfo;
}
