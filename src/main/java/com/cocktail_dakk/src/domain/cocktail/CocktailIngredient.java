package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.ingredient.Ingredient;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class CocktailIngredient {

    @Id @GeneratedValue
    private Long cocktailIngredientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ingredientId")
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cocktailInfoId")
    private CocktailInfo cocktailInfo;
}
