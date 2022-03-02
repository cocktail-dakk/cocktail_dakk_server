package com.cocktail_dakk.src.domain.cocktail;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CocktailFilterRepository {
    List<CocktailInfo> findSearchFilter(Pageable pageable, List<String> keywordName, Integer minAlcoholLevel, Integer maxAlcoholLevel, List<String> drinkName);
}
