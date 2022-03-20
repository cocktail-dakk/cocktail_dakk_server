package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.keyword.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CocktailKeywordRepository extends JpaRepository<CocktailKeyword, Long> {

    List<CocktailKeyword> findAllByKeyword(Keyword Keyword);
}
