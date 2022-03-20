package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CocktailInfoRepository extends JpaRepository<CocktailInfo, Long>, CocktailFilterRepository {
    @Query("SELECT distinct c FROM CocktailInfo c join fetch c.cocktailKeywords ck join fetch ck.keyword k where (c.koreanName like concat('%', :inputStr, '%')  or c.englishName like concat('%', :inputStr, '%') or c.ingredient like concat('%', :inputStr, '%') or k.keywordName like concat('%', :inputStr, '%')) and c.status='ACTIVE'")
    List<CocktailInfo> findSearch(Pageable pageable, @Param("inputStr") String inputStr);

    CocktailInfo findByCocktailInfoId(Long id);

    @Query("SELECT distinct c FROM CocktailInfo c join fetch c.cocktailKeywords ck join fetch ck.keyword k where c.status = 'ACTIVE'")
    List<CocktailInfo> findAllByStatus();

    @Query("SELECT distinct c FROM CocktailInfo c join fetch c.cocktailKeywords ck join fetch ck.keyword k where c.cocktailInfoId = ?1")
    Optional<CocktailInfo> findByCocktailIdToday(Long id);

}
