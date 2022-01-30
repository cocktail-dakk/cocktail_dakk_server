package com.cocktail_dakk.src.service.recommend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
@Slf4j
@EnableScheduling
@Configuration
public class TodayCocktailScheduler {
    private final TodayCocktailService todayCocktailService;

    //메일 오전 24시 생성
    @Scheduled(cron = "0 0 0 * * *")
    //@Scheduled(cron="0 0/1 * * * *") //1분
    public void todayCocktail(){
       todayCocktailService.getRandomCocktailId();
    }

}
