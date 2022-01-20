package java.com.cocktail_dakk.src.domain.cocktail;

import javax.persistence.*;
import java.com.cocktail_dakk.src.domain.Ingredient;

@Entity
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
