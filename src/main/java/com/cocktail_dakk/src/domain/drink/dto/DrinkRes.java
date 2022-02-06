package com.cocktail_dakk.src.domain.drink.dto;

import com.cocktail_dakk.src.domain.cocktail.CocktailDrink;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DrinkRes {
    private Long drinkId;
    private String drinkName;

    public DrinkRes(CocktailDrink cocktailDrink){
        this.drinkId=cocktailDrink.getDrink().getDrinkId();
        this.drinkName=cocktailDrink.getDrink().getDrinkName();
    }

    public DrinkRes(Drink drink){
        this.drinkId=drink.getDrinkId();
        this.drinkName=drink.getDrinkName();
    }

    public DrinkRes(UserDrink userDrink){
        this.drinkId = userDrink.getDrink().getDrinkId();
        this.drinkName = userDrink.getDrink().getDrinkName();
    }
}
