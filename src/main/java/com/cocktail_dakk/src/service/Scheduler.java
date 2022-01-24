package com.cocktail_dakk.src.service;

import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfoRepository;
import com.cocktail_dakk.src.domain.cocktail.CocktailToday;
import com.cocktail_dakk.src.domain.cocktail.CocktailTodayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@EnableScheduling
@Configuration
public class Scheduler {
    private final CocktailInfoRepository cocktailInfoRepository;
    private final CocktailTodayRepository cocktailTodayRepository;
    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);


    //메일 오전 24시 생성
    @Scheduled(cron = "0 0 0 * * *")
    //@Scheduled(cron="0/10 * * * * *") //10초
    public void todayCocktail(){
        logger.info(new Date() + "스케쥴러 실행");
        //DB 초기화
        cocktailTodayRepository.deleteAll();

        //랜덤 인덱스 중복 없이 5개 추출
        Long[] randomCocktailId = new Long[5];
        long cocktailCount = cocktailInfoRepository.count();

        getRandomIndex(randomCocktailId, cocktailCount);

        CocktailToday cocktailToday = new CocktailToday();
        for(int i=0; i<5; i++){
            //Optional<CocktailInfo> cocktail = cocktailInfoRepository.findById(randomCocktailId[i]);
            cocktailToday.getRandomId().add(randomCocktailId[i]);
            log.info("하하 {}", randomCocktailId[i]);
        }
        log.info("==끝==");

        cocktailTodayRepository.save(cocktailToday);
    }

    private void getRandomIndex(Long[] randomCocktailId, long cocktailCount) {
        Optional<CocktailInfo> cocktailInfo;
        for(int i = 0; i<5; i++){
            randomCocktailId[i] = Long.valueOf((long)(Math.random() * cocktailCount));
            cocktailInfo = cocktailInfoRepository.findById(randomCocktailId[i]);
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
