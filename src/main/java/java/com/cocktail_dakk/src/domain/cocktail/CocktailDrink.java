package java.com.cocktail_dakk.src.domain.cocktail;

import javax.persistence.*;
import java.com.cocktail_dakk.src.domain.Drink;

@Entity
public class CocktailDrink {

    @Id @GeneratedValue
    private Long cocktailDrinkId;

    @ManyToOne(fetch = FetchType.LAZY)
    private CocktailInfo cocktailInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Drink drink;
}
