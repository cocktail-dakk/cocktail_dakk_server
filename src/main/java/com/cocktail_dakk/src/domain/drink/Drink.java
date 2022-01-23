package com.cocktail_dakk.src.domain.drink;

import com.cocktail_dakk.src.domain.Status;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.cocktail_dakk.src.domain.cocktail.CocktailDrink;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "Drink")
public class Drink {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long drinkId;

    @NotNull
    private String drinkName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @OneToMany(mappedBy = "drink")
    List<CocktailDrink> cocktailDrinks=new ArrayList<>();

    @Builder
    public Drink(String drinkName, Status status){
        this.drinkName=drinkName;
        this.status=status;
    }
}
