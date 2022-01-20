package java.com.cocktail_dakk.src.domain.user;

import javax.persistence.*;
import java.com.cocktail_dakk.src.domain.cocktail.CocktailInfo;

@Entity
public class UserCocktail {

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
