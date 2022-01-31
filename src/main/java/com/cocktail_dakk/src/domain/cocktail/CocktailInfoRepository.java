package com.cocktail_dakk.src.domain.cocktail;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocktailInfoRepository extends JpaRepository<CocktailInfo, Long> {
    Page<CocktailInfo> findAllByKoreanNameContainingOrEnglishNameContainingOrIngredientContaining(Pageable pageable, String koreanName, String englishName, String ingredients);
    CocktailInfo findByCocktailInfoId(Long id);
}
