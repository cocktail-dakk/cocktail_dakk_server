package com.cocktail_dakk.src.domain.ingredient;

import com.cocktail_dakk.src.domain.Status;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.cocktail_dakk.src.domain.cocktail.CocktailIngredient;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "Ingredient")
public class Ingredient {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long ingredientId;

    @NotNull
    private String ingredientName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @OneToMany(mappedBy = "ingredient")
    List<CocktailIngredient> cocktailIngredients=new ArrayList<>();

    @Builder
    public Ingredient(String ingredientName, Status status){
        this.ingredientName=ingredientName;
        this.status=status;
    }

}
