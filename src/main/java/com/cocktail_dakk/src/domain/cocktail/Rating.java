package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.user.UserInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
public class Rating {

    @Builder
    public Rating(CocktailInfo cocktailInfo, UserInfo userInfo, float rating) {
        this.cocktailInfo = cocktailInfo;
        this.userInfo = userInfo;
        this.rating=rating;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    private float rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cocktailInfoId")
    private CocktailInfo cocktailInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInfoId")
    private UserInfo userInfo;
}
