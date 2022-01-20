package java.com.cocktail_dakk.src.domain.cocktail;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.com.cocktail_dakk.src.domain.ingredient.Ingredient;

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
