package java.com.cocktail_dakk.src.domain.cocktail;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.com.cocktail_dakk.src.domain.drink.Drink;

@Entity
@NoArgsConstructor
@Getter
public class CocktailDrink {

    public CocktailDrink(CocktailInfo cocktailInfo, Drink drink) {
        this.cocktailInfo = cocktailInfo;
        this.drink = drink;
    }

    @Id @GeneratedValue
    private Long cocktailDrinkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cocktailInfoId")
    private CocktailInfo cocktailInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="drinkId")
    private Drink drink;
}
