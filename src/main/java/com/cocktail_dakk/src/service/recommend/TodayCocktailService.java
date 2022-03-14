package com.cocktail_dakk.src.service.recommend;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.src.domain.Status;
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
            List<CocktailInfo> activeCocktailInfos = cocktailInfoRepository.findAllByStatus();
            int activeCocktailInfoCount = activeCocktailInfos.size();
            Integer[] randomCocktailId = new Integer[5];
            getRandomIndex(activeCocktailInfoCount, randomCocktailId);

            //오늘의 칵테일 id 저장
            CocktailToday cocktailToday = new CocktailToday();
            for (int i = 0; i < 5; i++) {
                CocktailInfo randomCocktailInfo = activeCocktailInfos.get(randomCocktailId[i]);
                Long cocktailInfoId = randomCocktailInfo.getCocktailInfoId();
                cocktailToday.getRandomId().add(cocktailInfoId);
                log.info("randomId ={}", cocktailInfoId);
            }
            cocktailTodayRepository.save(cocktailToday);

        } catch (Exception e){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    private void getRandomIndex(int activeCocktailInfoCount, Integer[] randomCocktailId) {
        for(int i = 0; i < 5; i++){
            randomCocktailId[i] = Integer.valueOf((int) (Math.random() * activeCocktailInfoCount));
            for (int j = 0; j < i; j++) {
                if (randomCocktailId[i].equals(randomCocktailId[j])) {
                    i--;
                    break;
                }
            }
        }
    }

}
