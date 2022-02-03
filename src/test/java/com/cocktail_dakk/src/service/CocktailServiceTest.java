package com.cocktail_dakk.src.service;

import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfoRepository;
import com.cocktail_dakk.src.domain.cocktail.CocktailToday;
import com.cocktail_dakk.src.domain.cocktail.CocktailTodayRepository;
import com.cocktail_dakk.src.domain.cocktail.dto.GetTodayCocktailInfoRes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class CocktailServiceTest {

    @Autowired
    private CocktailService cocktailService;
    @Autowired
    private CocktailTodayRepository cocktailTodayRepository;
    @Autowired
    private CocktailInfoRepository cocktailInfoRepository;


    @Test
    public void cocktailToday() throws Exception{

        //given
//        List<GetTodayCocktailInfoRes> todayCocktail = cocktailService.getTodayCocktail();
//
//        //when
//        CocktailInfo cocktail1 = createCocktail("Golden Dream", "골든 드림", "달콤하고 부드러운 맛 덕분에 주로 식후주로 사용되며, 이전 IBA 공식 칵테일에서도 식후주로 분류된 바 있다.",
//                "url111", "url222", "url333", 2);
//        CocktailInfo cocktail2 = createCocktail("Gold Rush", "골드 러쉬", "골드러쉬는 매우 간단한 음료입니다. 버번, 허니 시럽, 신선한 레몬 주스로 구성되어 있습니다.?위스키 사워와?설탕 대신 꿀을 넣습니다. 위스키와 꿀의 조합은 칵테일의 풍미와 식감을 변화시켜 골드러쉬를 먹을때는 음료를 먹는 느낌이 듭니다.",
//                "url1", "url2", "url3", 2);
//
//        List<GetTodayCocktailInfoRes> todayCocktail1 = cocktailService.getTodayCocktail();
//        for (GetTodayCocktailInfoRes getTodayCocktailInfoRes : todayCocktail1) {
//            System.out.println(getTodayCocktailInfoRes.getKoreanName());
//        }

        //then
    }

    private CocktailInfo createCocktail(String englishName, String koreanName, String description, String url1,
                                String url2, String url3, int level) {
        return CocktailInfo.builder()
                .englishName(englishName)
                .koreanName(koreanName)
                .description(description)
                .cocktailImageURL(url1)
                .cocktailBackgroundImageURL(url2)
                .recommendImageURL(url3)
                .alcoholLevel(level)
                .status(Status.ACTIVE)
                .build();
    }


}