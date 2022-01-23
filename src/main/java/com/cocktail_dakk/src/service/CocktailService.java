package com.cocktail_dakk.src.service;

import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfoRepository;
import com.cocktail_dakk.src.domain.cocktail.CocktailKeywordRepository;
import com.cocktail_dakk.src.domain.user.UserCocktailRepository;
import com.cocktail_dakk.src.domain.cocktail.dto.GetTodayCocktailInfoRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CocktailService {
    private final CocktailKeywordRepository cocktailKeywordRepository;
    private final UserCocktailRepository userCocktailRepository;
    private final CocktailInfoRepository cocktailInfoRepository;

    //오늘의 칵테일 추천
    public GetTodayCocktailInfoRes getTodayCocktail() {
        long cocktailCount = cocktailInfoRepository.count();
        Long randomId;
        Optional<CocktailInfo> cocktailInfo;

        do{
            randomId = Long.valueOf((long)(Math.random() * cocktailCount +1));
            cocktailInfo = cocktailInfoRepository.findById(randomId);
        } while(cocktailInfo.isEmpty());

        return new GetTodayCocktailInfoRes(cocktailInfo.get());
    }

}
