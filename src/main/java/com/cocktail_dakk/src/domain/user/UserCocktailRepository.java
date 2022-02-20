package com.cocktail_dakk.src.domain.user;

import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserCocktailRepository extends JpaRepository<UserCocktail, Long> {
    boolean existsEventLikeByUserInfoAndCocktailInfo(UserInfo userInfo, CocktailInfo cocktailInfo);
    Optional<UserCocktail> findByUserInfoAndCocktailInfo(UserInfo userInfo, CocktailInfo cocktailInfo);
}
