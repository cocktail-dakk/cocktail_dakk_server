package java.com.cocktail_dakk.src.domain.cocktail;

import javax.persistence.*;
import java.com.cocktail_dakk.src.domain.Ingredient;

@Entity
public class CocktailIngredient {

    @Id @GeneratedValue
    private Long cocktailIngredientId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    private CocktailInfo cocktailInfo;
}
