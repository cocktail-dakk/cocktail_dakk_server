package com.cocktail_dakk.src.domain.drink;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    Drink findByDrinkName(String name);
}
