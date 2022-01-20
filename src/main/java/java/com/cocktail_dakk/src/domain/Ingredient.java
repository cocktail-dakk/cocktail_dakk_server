package java.com.cocktail_dakk.src.domain;

import javax.persistence.*;
import java.com.cocktail_dakk.src.domain.cocktail.CocktailIngredient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredient {

    @Id @GeneratedValue
    private Long ingredientId;

    private String ingredientName;

    @Enumerated(EnumType.STRING)
    private String status;

    @OneToMany(mappedBy = "ingredient")
    List<CocktailIngredient> cocktailIngredients=new ArrayList<>();

}
