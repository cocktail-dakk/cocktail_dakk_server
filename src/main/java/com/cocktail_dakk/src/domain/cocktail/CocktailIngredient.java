package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.ingredient.Ingredient;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class CocktailIngredient {

    public CocktailIngredient(CocktailInfo cocktailInfo, Ingredient ingredient, String unit, Double volume) {
        this.cocktailInfo = cocktailInfo;
        this.ingredient = ingredient;
        cocktailInfo.getCocktailIngredients().add(this);
        ingredient.getCocktailIngredients().add(this);

        this.unit = unit;
        this.volume = volume;
    }

    @Id @GeneratedValue
    private Long cocktailIngredientId;

    private String unit;

    private Double volume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ingredientId")
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cocktailInfoId")
    private CocktailInfo cocktailInfo;
}
