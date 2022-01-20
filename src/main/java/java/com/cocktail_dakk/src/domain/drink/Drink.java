package java.com.cocktail_dakk.src.domain.drink;

import lombok.Getter;

import javax.persistence.*;
import java.com.cocktail_dakk.src.domain.Status;
import java.com.cocktail_dakk.src.domain.cocktail.CocktailDrink;
import java.util.ArrayList;
import java.util.List;

@Entity
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
