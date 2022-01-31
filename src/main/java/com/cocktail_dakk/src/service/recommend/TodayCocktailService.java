package com.cocktail_dakk.src.service.recommend;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.src.domain.cocktail.*;
import com.cocktail_dakk.src.domain.cocktail.dto.GetTodayCocktailInfoRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.cocktail_dakk.config.BaseResponseStatus.DATABASE_ERROR;
import static com.cocktail_dakk.config.BaseResponseStatus.REQUEST_ERROR;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class TodayCocktailService {
    private final CocktailTodayRepository cocktailTodayRepository;
    private final CocktailInfoRepository cocktailInfoRepository;

    //오늘의 칵테일 랜덤
    public List<GetTodayCocktailInfoRes> getTodayCocktail() throws BaseException {
        try {
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

        } catch (Exception e){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //랜덤 인덱스 추출
    @Transactional
    public void getRandomCocktailId() throws BaseException{
        try {
            //DB 초기화
            cocktailTodayRepository.deleteAll();

            //랜덤 인덱스 중복 없이 5개 추출
            Long[] randomCocktailId = new Long[5];
            long cocktailCount = cocktailInfoRepository.count();
            Long lastInsertId = cocktailInfoRepository.findAll().get((int) cocktailCount-1).getCocktailInfoId(); //마지막에 들어간 id

            getRandomIndexes(randomCocktailId, lastInsertId);

            CocktailToday cocktailToday = new CocktailToday();
            for(int i=0; i<5; i++){
                cocktailToday.getRandomId().add(randomCocktailId[i]);
                log.info("randomId ={}", randomCocktailId[i]);
            }
            cocktailTodayRepository.save(cocktailToday);

        } catch (Exception e){
            throw new BaseException(REQUEST_ERROR);
        }
    }

    private void getRandomIndexes(Long[] randomCocktailId, long cocktailCount) {
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

}
