package com.cocktail_dakk.src.domain.user;

import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserCocktailRepository extends JpaRepository<UserCocktail, Long> {
    boolean existsLikeByUserInfoAndCocktailInfo(UserInfo userInfo, CocktailInfo cocktailInfo);
    Optional<UserCocktail> findByUserInfoAndCocktailInfo(UserInfo userInfo, CocktailInfo cocktailInfo);
    @Query("SELECT distinct u FROM UserCocktail u join fetch u.userInfo ui join fetch u.cocktailInfo ci join fetch ci.cocktailKeywords ck join fetch ck.keyword where  ui.userInfoId = ?1")
    List<UserCocktail> findByUserInfo(Long userId);
}
