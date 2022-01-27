package com.cocktail_dakk.src.domain.drink.dto;


import com.cocktail_dakk.src.domain.drink.Drink;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DrinkRes {
    private String drinkName;

    public DrinkRes(Drink drink){
        this.drinkName = drink.getDrinkName();
    }
}
