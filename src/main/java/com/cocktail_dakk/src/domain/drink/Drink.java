package com.cocktail_dakk.src.domain.drink;

import com.cocktail_dakk.src.domain.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.cocktail_dakk.src.domain.cocktail.CocktailDrink;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Drink {

    @Id @GeneratedValue
    private Long drinkId;

    private String drinkName;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "drink")
    List<CocktailDrink> cocktailDrinks=new ArrayList<>();
}
