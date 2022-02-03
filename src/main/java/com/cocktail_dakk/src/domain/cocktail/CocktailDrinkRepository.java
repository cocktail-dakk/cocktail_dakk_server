package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.drink.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CocktailDrinkRepository extends JpaRepository<CocktailDrink, Long> {
    List<CocktailDrink> findAllByDrink(Drink drink);
}
