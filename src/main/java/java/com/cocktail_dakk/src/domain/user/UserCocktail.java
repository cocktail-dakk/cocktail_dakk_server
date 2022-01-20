package java.com.cocktail_dakk.src.domain.user;

import lombok.Getter;

import javax.persistence.*;
import java.com.cocktail_dakk.src.domain.cocktail.CocktailInfo;

@Entity
@Getter
public class UserCocktail {

    public UserCocktail() {
    }

    public UserCocktail(UserInfo userInfo, CocktailInfo cocktailInfo, Double rating, String review) {
        this.rating = rating;
        this.userInfo = userInfo;
        this.cocktailInfo = cocktailInfo;
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
