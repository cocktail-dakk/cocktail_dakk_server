package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CocktailInfoRepository extends JpaRepository<CocktailInfo, Long>, CocktailFilterRepository {
    @Query("SELECT distinct c FROM CocktailInfo c join c.cocktailKeywords ck join ck.keyword k where (c.koreanName like concat('%', :koreanName, '%')  or c.englishName like concat('%', :englishName, '%') or c.ingredient like concat('%', :ingredients, '%') or k.keywordName like concat('%', :keywordName, '%')) and c.status='ACTIVE'")
    List<CocktailInfo> findSearch(Pageable pageable, @Param("koreanName") String koreanName, @Param("englishName")String englishName, @Param("ingredients") String ingredients, @Param("keywordName") String keywordName);

    CocktailInfo findByCocktailInfoId(Long id);

    @Query("SELECT distinct c from CocktailInfo c join fetch c.cocktailKeywords where c.status = 'ACTIVE'")
    List<CocktailInfo> findAllByStatus();
}
