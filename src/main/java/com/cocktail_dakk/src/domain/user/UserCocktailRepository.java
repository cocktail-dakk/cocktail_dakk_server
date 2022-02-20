package com.cocktail_dakk.src.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserCocktailRepository extends JpaRepository<UserCocktail, Long> {

    boolean existsEventLikeByUserInfoIdAndCocktailInfoId(Long userInfoId, Long cocktailInfoId);
    Optional<UserCocktail> findByUserInfoIdAndCocktailInfoId(Long userInfoId, Long cocktailInfoId);
}
