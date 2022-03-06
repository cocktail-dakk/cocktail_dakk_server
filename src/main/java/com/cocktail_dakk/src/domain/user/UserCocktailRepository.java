package com.cocktail_dakk.src.domain.user;

import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserCocktailRepository extends JpaRepository<UserCocktail, Long> {
    boolean existsLikeByUserInfoAndCocktailInfo(UserInfo userInfo, CocktailInfo cocktailInfo);
    Optional<UserCocktail> findByUserInfoAndCocktailInfo(UserInfo userInfo, CocktailInfo cocktailInfo);
    List<UserCocktail> findByUserInfo(UserInfo userInfo);
}
