package com.cocktail_dakk.src.service.recommend;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.src.domain.cocktail.CocktailDrink;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.CocktailKeyword;
import com.cocktail_dakk.src.domain.cocktail.CocktailKeywordRepository;
import com.cocktail_dakk.src.domain.cocktail.dto.GetUserRecommendationRes;
import com.cocktail_dakk.src.domain.cocktail.dto.UserRecommendationList;
import com.cocktail_dakk.src.domain.drink.Drink;
import com.cocktail_dakk.src.domain.user.UserDrink;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.domain.user.UserKeyword;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.cocktail_dakk.config.BaseResponseStatus.REQUEST_ERROR;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserCocktailService {

    private final CocktailKeywordRepository cocktailKeywordRepository;
    //키워드 추천 칵테일
    public GetUserRecommendationRes getUserRecommendCocktail(UserInfo userInfo) throws BaseException {

        try {
            Integer userAlcohol = userInfo.getAlcoholLevel();

            List<UserDrink> userDrinks = userInfo.getUserDrinks();

            List<UserKeyword> userKeywords = userInfo.getUserKeywords();
            Set<CocktailInfo> cocktailByUser1 = new LinkedHashSet<>(); //1차
            Set<CocktailInfo> cocktailByUser2 = new LinkedHashSet<>(); //2차
            Set<CocktailInfo> cocktailByUser3 = new LinkedHashSet<>(); //3차

            //유저의 키워드, 도수, 기주에 따라 추출
            getUserRecommend(userAlcohol, userDrinks, userKeywords, cocktailByUser1, cocktailByUser2, cocktailByUser3);

            List<UserRecommendationList> getUserRecommendRes = cocktailByUser1.stream()
                    .map(UserRecommendationList::new)
                    .collect(Collectors.toList());

            if(getUserRecommendRes.size() > 5){
                return new GetUserRecommendationRes(userInfo.getNickname(), getUserRecommendRes.subList(0,5));
            }
            else{
                return new GetUserRecommendationRes(userInfo.getNickname(), getUserRecommendRes);
            }
        } catch (Exception e){
            throw new BaseException(REQUEST_ERROR);
        }

    }

    private void getUserRecommend(Integer userAlcohol, List<UserDrink> userDrinks, List<UserKeyword> userKeywords, Set<CocktailInfo> cocktailByUser1, Set<CocktailInfo> cocktailByUser2, Set<CocktailInfo> cocktailByUser3) {
        for (UserKeyword userKeyword : userKeywords) {
            List<CocktailKeyword> allByKeyword = cocktailKeywordRepository.findAllByKeyword(userKeyword.getKeyword());
            for (CocktailKeyword cocktailKeyword : allByKeyword) {
                //범위 내에 존재한다면 리스트에 추가
                CocktailInfo cocktailInfo = cocktailKeyword.getCocktailInfo();

                Integer cocktailAlcohol = cocktailInfo.getAlcoholLevel();

                if( (userAlcohol - userAlcohol /2) <=  cocktailAlcohol &&
                        cocktailAlcohol <= (userAlcohol + userAlcohol /2) ){
                    for (UserDrink userDrink  : userDrinks) {
                        List<CocktailDrink> cocktailDrinks = cocktailInfo.getCocktailDrinks();
                        List<Drink> drinks = cocktailDrinks.stream()
                                .map(CocktailDrink::getDrink)
                                .collect(Collectors.toList());

                        if ( drinks.contains(userDrink.getDrink()) ){
                            cocktailByUser1.add(cocktailInfo);
                        }
                        else{
                            cocktailByUser2.add(cocktailInfo);
                        }
                    }
                }
                //범위 안에 존재하지 않는다면
                else{
                    cocktailByUser3.add(cocktailInfo);
                }
            }
        }

        cocktailByUser1.addAll(cocktailByUser2);
        cocktailByUser1.addAll(cocktailByUser3);
    }
}
