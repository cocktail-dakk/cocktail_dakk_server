package com.cocktail_dakk.src.service;

import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfoRepository;
import com.cocktail_dakk.src.domain.cocktail.dto.GetTodayCocktailInfoRes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CocktailServiceTest {
    @Autowired
    private CocktailService cocktailService;
    @Autowired
    private CocktailInfoRepository cocktailInfoRepository;
    
    @Test
    public void todayRecommendCocktail() throws Exception{
        //given
        CocktailInfo cocktailInfo1 = createCocktailInfo("21st Century", "21세기",
                "쓰다", "1234", "5678", "91011");
        CocktailInfo cocktailInfo2 = createCocktailInfo("english name", "영어2 이름", "달달", "url1",
                "url2", "url3");
        CocktailInfo cocktailInfo3 = createCocktailInfo("test3 name", "영어3 이름", "달달", "url1",
                "url2", "url3");
        CocktailInfo cocktailInfo4 = createCocktailInfo("test4 name", "영어4 이름", "달달", "url1",
                "url2", "url3");

        //when
        cocktailInfoRepository.save(cocktailInfo1);
        cocktailInfoRepository.save(cocktailInfo2);
        cocktailInfoRepository.save(cocktailInfo3);
        cocktailInfoRepository.save(cocktailInfo4);

        GetTodayCocktailInfoRes todayCocktail = cocktailService.getTodayCocktail();
        GetTodayCocktailInfoRes todayCocktail2 = cocktailService.getTodayCocktail();

        //then
        System.out.println(todayCocktail.getCocktailInfoId());
        System.out.println(todayCocktail2.getCocktailInfoId());
    }

    private CocktailInfo createCocktailInfo(String englishName, String koreanName, String description, String cocktailImageURL, String cocktailBackgroundImageURL, String recommendImageURL) {
       return CocktailInfo.builder()
                .englishName(englishName)
                .koreanName(koreanName)
                .description(description)
                .cocktailImageURL(cocktailImageURL)
                .cocktailBackgroundImageURL(cocktailBackgroundImageURL)
                .recommendImageURL(recommendImageURL)
                .build();
    }

}