package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.user.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
public class Rating {

    public Rating(CocktailInfo cocktailInfo, UserInfo userInfo, BigDecimal rating) {
        this.cocktailInfo = cocktailInfo;
        this.userInfo = userInfo;
        this.rating=rating;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    private BigDecimal rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cocktailInfoId")
    private CocktailInfo cocktailInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInfoId")
    private UserInfo userInfo;
}
