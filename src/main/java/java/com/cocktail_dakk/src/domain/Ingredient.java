package java.com.cocktail_dakk.src.domain;

import lombok.Getter;

import javax.persistence.*;
import java.com.cocktail_dakk.src.domain.cocktail.CocktailIngredient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Ingredient {

    @Id @GeneratedValue
    private Long ingredientId;

    private String ingredientName;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "ingredient")
    List<CocktailIngredient> cocktailIngredients=new ArrayList<>();

}
