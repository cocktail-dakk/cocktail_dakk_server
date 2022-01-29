package com.cocktail_dakk.src.service;

import com.cocktail_dakk.src.domain.cocktail.*;
import com.cocktail_dakk.src.domain.cocktail.dto.GetTodayCocktailInfoRes;
import com.cocktail_dakk.src.domain.cocktail.dto.GetUserRecommendRes;
import com.cocktail_dakk.src.domain.drink.Drink;
import com.cocktail_dakk.src.domain.user.UserDrink;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.domain.user.UserKeyword;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CocktailService {
    private final CocktailTodayRepository cocktailTodayRepository;
    private final CocktailInfoRepository cocktailInfoRepository;
    private final CocktailKeywordRepository cocktailKeywordRepository;

    //오늘의 칵테일 랜덤
    public List<GetTodayCocktailInfoRes> getTodayCocktail() {
        List<CocktailToday> cocktailTodays = cocktailTodayRepository.findAll();
        CocktailToday cocktailToday = cocktailTodays.get((int)(cocktailTodayRepository.count()-1));
        List<Long> randomId = cocktailToday.getRandomId();

        List<CocktailInfo> cocktailInfos = new ArrayList<>();
        for (Long random : randomId) {
            Optional<CocktailInfo> cocktailInfo = cocktailInfoRepository.findById(random);
            cocktailInfos.add(cocktailInfo.get());
        }
        return cocktailInfos.stream()
                .map(GetTodayCocktailInfoRes::new)
                .collect(Collectors.toList());
    }

    //랜덤 인덱스 추출
    @Transactional
    public void getRandomCocktailId() {
        //DB 초기화
        cocktailTodayRepository.deleteAll();

        //랜덤 인덱스 중복 없이 5개 추출
        Long[] randomCocktailId = new Long[5];
        long cocktailCount = cocktailInfoRepository.count();
        Long lastInsertId = cocktailInfoRepository.findAll().get((int) cocktailCount-1).getCocktailInfoId(); //마지막에 들어간 id

        getRandomIndex(randomCocktailId, lastInsertId);

        CocktailToday cocktailToday = new CocktailToday();
        for(int i=0; i<5; i++){
            cocktailToday.getRandomId().add(randomCocktailId[i]);
            log.info("randomId ={}", randomCocktailId[i]);
        }

        cocktailTodayRepository.save(cocktailToday);
    }

    private void getRandomIndex(Long[] randomCocktailId, long cocktailCount) {
        for(int i = 0; i<5; i++){
            randomCocktailId[i] = Long.valueOf((long)(Math.random() * (cocktailCount + 1)));
            Optional<CocktailInfo> cocktailInfo = cocktailInfoRepository.findById(randomCocktailId[i]);
            //존재하지 않는 인덱스라면
            if(cocktailInfo.isEmpty()){
                i -- ;
                continue;
            }

            for (int j = 0; j < i; j++) {
                if(randomCocktailId[i].equals(randomCocktailId[j])){
                    i--;
                    break;
                }
            }
        }
    }

    //키워드 추천 칵테일
    public List<GetUserRecommendRes> getUserRecommendCocktail(UserInfo userInfo) {

        //유저 도수
        Integer userAlcohol = userInfo.getAlcoholLevel();
        //유저 기주
        List<UserDrink> userDrinks = userInfo.getUserDrinks();
        //유저 키워드
        List<UserKeyword> userKeywords = userInfo.getUserKeywords();
        Set<CocktailInfo> cocktailByUser1 = new LinkedHashSet<>(); //1차
        Set<CocktailInfo> cocktailByUser2 = new LinkedHashSet<>(); //2차
        Set<CocktailInfo> cocktailByUser3 = new LinkedHashSet<>(); //3차

        //유저의 키워드, 도수, 기주에 따라 추출
        getUserRecommend(userAlcohol, userDrinks, userKeywords, cocktailByUser1, cocktailByUser2, cocktailByUser3);

        List<GetUserRecommendRes> getUserRecommedRes = cocktailByUser1.stream()
                .map(GetUserRecommendRes::new)
                .collect(Collectors.toList());

        if(getUserRecommedRes.size() > 5){
            return getUserRecommedRes.subList(0,5);
        }
        else{
            return getUserRecommedRes;
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