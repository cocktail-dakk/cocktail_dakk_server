package com.cocktail_dakk.src.service;

import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfoRepository;
import com.cocktail_dakk.src.domain.cocktail.CocktailToday;
import com.cocktail_dakk.src.domain.cocktail.CocktailTodayRepository;
import com.cocktail_dakk.src.domain.cocktail.dto.GetTodayCocktailInfoRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CocktailService {
    private final CocktailTodayRepository cocktailTodayRepository;
    private final CocktailInfoRepository cocktailInfoRepository;

    //오늘의 칵테일 랜덤
    public List<GetTodayCocktailInfoRes> getTodayCocktail() {
        List<CocktailToday> cocktailTodays = cocktailTodayRepository.findAll();
        CocktailToday cocktailToday = cocktailTodays.get(0);
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
        Long lastInsertId = cocktailInfoRepository.findAll().get((int) cocktailCount).getCocktailInfoId();

        getRandomIndex(randomCocktailId, cocktailCount);

        CocktailToday cocktailToday = new CocktailToday();
        for(int i=0; i<5; i++){
            cocktailToday.getRandomId().add(randomCocktailId[i]);
            log.info("randomId ={}", randomCocktailId[i]);
        }

        cocktailTodayRepository.save(cocktailToday);
    }

    private void getRandomIndex(Long[] randomCocktailId, long cocktailCount) {
        for(int i = 0; i<5; i++){
            randomCocktailId[i] = Long.valueOf((long)(Math.random() * cocktailCount));
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

}