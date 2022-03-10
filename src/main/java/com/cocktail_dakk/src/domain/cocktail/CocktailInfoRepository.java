package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CocktailInfoRepository extends JpaRepository<CocktailInfo, Long>, CocktailFilterRepository {
    @Query("SELECT distinct c FROM CocktailInfo c join c.cocktailKeywords ck join ck.keyword k where (c.koreanName like concat('%', :inputStr, '%')  or c.englishName like concat('%', :inputStr, '%') or c.ingredient like concat('%', :inputStr, '%') or k.keywordName like concat('%', :inputStr, '%')) and c.status='ACTIVE'")
    List<CocktailInfo> findSearch(Pageable pageable, @Param("inputStr") String inputStr);

    CocktailInfo findByCocktailInfoId(Long id);
    List<CocktailInfo> findAllByStatus(Status status);
}
