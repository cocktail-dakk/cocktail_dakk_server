package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    boolean existsRatingByUserInfoAndCocktailInfo(UserInfo userInfo, CocktailInfo cocktailInfo);
    Optional<Rating> findByUserInfoAndCocktailInfo(UserInfo userInfo, CocktailInfo cocktailInfo);
}
