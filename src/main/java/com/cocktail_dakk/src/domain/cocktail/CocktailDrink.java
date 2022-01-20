package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.drink.Drink;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
