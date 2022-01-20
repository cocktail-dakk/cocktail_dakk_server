package java.com.cocktail_dakk.src.domain;

import javax.persistence.*;
import java.com.cocktail_dakk.src.domain.cocktail.CocktailDrink;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Drink {

    @Id @GeneratedValue
    private Long drinkId;

    private String drinkName;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "drink")
    List<CocktailDrink> cocktailDrinks=new ArrayList<>();
}
