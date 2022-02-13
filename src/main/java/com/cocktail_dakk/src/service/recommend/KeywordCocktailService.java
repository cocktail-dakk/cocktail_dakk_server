package com.cocktail_dakk.src.service.recommend;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.src.domain.cocktail.*;
import com.cocktail_dakk.src.domain.cocktail.dto.GetRecommendationListRes;
import com.cocktail_dakk.src.domain.cocktail.dto.GetRecommendationRes;
import com.cocktail_dakk.src.domain.drink.Drink;
import com.cocktail_dakk.src.domain.drink.DrinkRepository;
import com.cocktail_dakk.src.domain.keyword.Keyword;
import com.cocktail_dakk.src.domain.keyword.KeywordRepository;
import com.cocktail_dakk.src.domain.user.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.cocktail_dakk.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class KeywordCocktailService {
    private final CocktailKeywordRepository cocktailKeywordRepository;
    private final KeywordRepository keywordRepository;
    private final DrinkRepository drinkRepository;
    private final CocktailDrinkRepository cocktailDrinkRepository;

    //사용자가 지정하지 않은 키워드로 칵테일 추천
    public GetRecommendationListRes getKeywordRecommendation(UserInfo userInfo) throws BaseException{
        try {
            Keyword randomKeyword = getRandomKeyword(keywordRepository.count(), userInfo);
            List<CocktailKeyword> cocktailKeywords = cocktailKeywordRepository.findAllByKeyword(randomKeyword);
            List<GetRecommendationRes> getRecommendKeywordRes = cocktailKeywords.stream()
                    .map(CocktailKeyword::getCocktailInfo)
                    .map(GetRecommendationRes::new)
                    .collect(Collectors.toList());

            if (getRecommendKeywordRes.size() > 9) {
                getRecommendKeywordRes = getRecommendKeywordRes.subList(0, 9);
            }

            String description = randomKeyword.getKeywordName();
            String tag = "키워드";

            return GetRecommendationListRes.builder()
                    .tag(tag)
                    .description(description)
                    .recommendationRes(getRecommendKeywordRes)
                    .build();
        } catch (Exception e){
            throw new BaseException(DATABASE_ERROR);
        }


    }

    //사용자가 지정하지 않은 기주로 칵테일 추천
    public GetRecommendationListRes getDrinkRecommendation(UserInfo userInfo) throws BaseException{
        try {
            Drink randomDrink = getRandomDrink(drinkRepository.count(), userInfo);
            List<CocktailDrink> cocktailDrinks = cocktailDrinkRepository.findAllByDrink(randomDrink);
            List<GetRecommendationRes> getRecommendationRes = cocktailDrinks.stream()
                    .map(CocktailDrink::getCocktailInfo)
                    .map(GetRecommendationRes::new)
                    .collect(Collectors.toList());
            if (getRecommendationRes.size() > 9) {
                getRecommendationRes = getRecommendationRes.subList(0, 9);
            }

            String description = randomDrink.getDrinkName();
            String tag = "기주";

            return GetRecommendationListRes.builder()
                    .tag(tag)
                    .description(description)
                    .recommendationRes(getRecommendationRes)
                    .build();
        } catch (Exception e){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    private Keyword getRandomKeyword(long count, UserInfo userInfo) throws BaseException {
        Long randomId;
        Optional<Keyword> byId;
        List<Keyword> userKeywords = getUserKeywords(userInfo);
        try{
            do{
                randomId = Long.valueOf((long)(Math.random() * count +1));
                byId = keywordRepository.findById(randomId);

            } while(byId.isEmpty() || userKeywords.contains(byId.get()));

        }catch (Exception e){
            throw new BaseException(DATABASE_ERROR);
        }
        return byId.get();
    }

    private Drink getRandomDrink(long count, UserInfo userInfo) throws BaseException {
        Long randomId;
        Optional<Drink> byId;
        List<Drink> userDrinks = getUserDrinks(userInfo);
        try{
            do{
                randomId = Long.valueOf((long)(Math.random() * count +1));
                byId = drinkRepository.findById(randomId);
            } while(byId.isEmpty() || userDrinks.contains(byId.get()));

        } catch (Exception e){
            throw new BaseException(DATABASE_ERROR);
        }

        return byId.get();
    }

    private List<Keyword> getUserKeywords(UserInfo userInfo) {
        List<Keyword> userKeywords = userInfo.getUserKeywords().stream()
                .map(UserKeyword::getKeyword)
                .collect(Collectors.toList());
        return userKeywords;
    }

    private List<Drink> getUserDrinks(UserInfo userInfo) {
        List<Drink> userDrinks = userInfo.getUserDrinks().stream()
                .map(UserDrink::getDrink)
                .collect(Collectors.toList());
        return userDrinks;
    }

}