package com.cocktail_dakk.src.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
@Slf4j
@EnableScheduling
@Configuration
public class Scheduler {
    private final CocktailService cocktailService;

    //메일 오전 24시 생성
    @Scheduled(cron = "0 0 0 * * *")
    //@Scheduled(cron="0/10 * * * * *") //10초
    public void todayCocktail(){
       cocktailService.getRandomCocktailId();
    }


}