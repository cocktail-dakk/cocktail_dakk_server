package com.cocktail_dakk.src.service;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfoRepository;
import com.cocktail_dakk.src.domain.cocktail.dto.SearchCocktailInfoRes;
import com.cocktail_dakk.src.domain.drink.DrinkRepository;
import com.cocktail_dakk.src.domain.drink.dto.DrinkRes;
import com.cocktail_dakk.src.domain.keyword.KeywordRepository;
import com.cocktail_dakk.src.domain.keyword.dto.KeywordRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.cocktail_dakk.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@Transactional(readOnly = true)
public class FilterCocktailInfoService {

    @Autowired
    CocktailInfoRepository cocktailInfoRepository;

    @Autowired
    KeywordRepository keywordRepository;

    @Autowired
    DrinkRepository drinkRepository;

    public Slice<SearchCocktailInfoRes> findByFilterAll(Pageable pageable, List<String> keywordName, Integer minAlcoholLevel, Integer maxAlcoholLevel, List<String> drinkName) throws BaseException {
        try{
            List<SearchCocktailInfoRes> result = cocktailInfoRepository.findSearchFilter(pageable, keywordName, minAlcoholLevel, maxAlcoholLevel, drinkName)
                    .stream()
                    .map(SearchCocktailInfoRes::new)
                    .collect(Collectors.toList());

            return new SliceImpl<>(result);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<KeywordRes> findAllKeyword() throws BaseException{
        try {
            return keywordRepository.findAll()
                    .stream()
                    .map(KeywordRes::new)
                    .collect(Collectors.toList());
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<DrinkRes> findAllDrink() throws BaseException{
        try {
            return drinkRepository.findAll()
                    .stream()
                    .map(DrinkRes::new)
                    .collect(Collectors.toList());
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
