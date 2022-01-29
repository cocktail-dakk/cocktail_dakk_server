package com.cocktail_dakk.src.service;

import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.cocktail.*;
import com.cocktail_dakk.src.domain.keyword.KeywordRepository;
import com.cocktail_dakk.src.domain.user.UserInfoRepository;
import com.cocktail_dakk.src.domain.user.UserKeywordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
    @Autowired
    private KeywordRepository keywordRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserKeywordRepository userKeywordRepository;
    @Autowired
    private CocktailKeywordRepository cocktailKeywordRepository;

    @Test
    @Transactional
    @DisplayName("겹치지 않는 랜덤 id를 추출한다.")
    public void cocktailToday() throws Exception{

        //given
        CocktailInfo cocktail1 = createCocktail(1L,"Golden Dream", "골든 드림", "달콤하고 부드러운 맛 덕분에 주로 식후주로 사용되며, 이전 IBA 공식 칵테일에서도 식후주로 분류된 바 있다.",
                "url111", "url222", "url333", 2);
        CocktailInfo cocktail2 = createCocktail(3L,"test2", "테스트2", "골드러쉬는 매우 간단한 음료입니다. 버번, 허니 시럽, 신선한 레몬 주스로 구성되어 있습니다.?위스키 사워와?설탕 대신 꿀을 넣습니다. 위스키와 꿀의 조합은 칵테일의 풍미와 식감을 변화시켜 골드러쉬를 먹을때는 음료를 먹는 느낌이 듭니다.",
                "url1", "url2", "url3", 2);
        CocktailInfo cocktail3 = createCocktail(5L,"test3", "테스트3", "test3",
                "url1", "url2", "url3", 2);
        CocktailInfo cocktail4 = createCocktail(10L,"test4", "테스트4", "골드러쉬는 매우 간단한 음료입니다. 버번, 허니 시럽, 신선한 레몬 주스로 구성되어 있습니다.?위스키 사워와?설탕 대신 꿀을 넣습니다. 위스키와 꿀의 조합은 칵테일의 풍미와 식감을 변화시켜 골드러쉬를 먹을때는 음료를 먹는 느낌이 듭니다.",
                "url1", "url2", "url3", 2);
        CocktailInfo cocktail5 = createCocktail(12L,"test5", "테스트5", "골드러쉬는 매우 간단한 음료입니다. 버번, 허니 시럽, 신선한 레몬 주스로 구성되어 있습니다.?위스키 사워와?설탕 대신 꿀을 넣습니다. 위스키와 꿀의 조합은 칵테일의 풍미와 식감을 변화시켜 골드러쉬를 먹을때는 음료를 먹는 느낌이 듭니다.",
                "url1", "url2", "url3", 2);
        CocktailInfo cocktail6 = createCocktail(13L,"test6", "테스트6", "골드러쉬는 매우 간단한 음료입니다. 버번, 허니 시럽, 신선한 레몬 주스로 구성되어 있습니다.?위스키 사워와?설탕 대신 꿀을 넣습니다. 위스키와 꿀의 조합은 칵테일의 풍미와 식감을 변화시켜 골드러쉬를 먹을때는 음료를 먹는 느낌이 듭니다.",
                "url1", "url2", "url3", 2);
        CocktailInfo cocktail7 = createCocktail(15L,"test7", "테스트7", "골드러쉬는 매우 간단한 음료입니다. 버번, 허니 시럽, 신선한 레몬 주스로 구성되어 있습니다.?위스키 사워와?설탕 대신 꿀을 넣습니다. 위스키와 꿀의 조합은 칵테일의 풍미와 식감을 변화시켜 골드러쉬를 먹을때는 음료를 먹는 느낌이 듭니다.",
                "url1", "url2", "url3", 2);
        CocktailInfo cocktail8 = createCocktail(16L,"test8", "테스트8", "골드러쉬는 매우 간단한 음료입니다. 버번, 허니 시럽, 신선한 레몬 주스로 구성되어 있습니다.?위스키 사워와?설탕 대신 꿀을 넣습니다. 위스키와 꿀의 조합은 칵테일의 풍미와 식감을 변화시켜 골드러쉬를 먹을때는 음료를 먹는 느낌이 듭니다.",
                "url1", "url2", "url3", 2);
        CocktailInfo cocktail9 = createCocktail(19L,"test9", "테스트9", "골드러쉬는 매우 간단한 음료입니다. 버번, 허니 시럽, 신선한 레몬 주스로 구성되어 있습니다.?위스키 사워와?설탕 대신 꿀을 넣습니다. 위스키와 꿀의 조합은 칵테일의 풍미와 식감을 변화시켜 골드러쉬를 먹을때는 음료를 먹는 느낌이 듭니다.",
                "url1", "url2", "url3", 2);
        CocktailInfo cocktail10 = createCocktail(20L,"test10", "테스트10", "골드러쉬는 매우 간단한 음료입니다. 버번, 허니 시럽, 신선한 레몬 주스로 구성되어 있습니다.?위스키 사워와?설탕 대신 꿀을 넣습니다. 위스키와 꿀의 조합은 칵테일의 풍미와 식감을 변화시켜 골드러쉬를 먹을때는 음료를 먹는 느낌이 듭니다.",
                "url1", "url2", "url3", 2);
        CocktailInfo cocktail11 = createCocktail(21L,"test11", "테스트11", "골드러쉬는 매우 간단한 음료입니다. 버번, 허니 시럽, 신선한 레몬 주스로 구성되어 있습니다.?위스키 사워와?설탕 대신 꿀을 넣습니다. 위스키와 꿀의 조합은 칵테일의 풍미와 식감을 변화시켜 골드러쉬를 먹을때는 음료를 먹는 느낌이 듭니다.",
                "url1", "url2", "url3", 2);

        cocktailInfoRepository.save(cocktail1);
        cocktailInfoRepository.save(cocktail2);
        cocktailInfoRepository.save(cocktail3);
        cocktailInfoRepository.save(cocktail4);
        cocktailInfoRepository.save(cocktail5);
        cocktailInfoRepository.save(cocktail6);
        cocktailInfoRepository.save(cocktail7);
        cocktailInfoRepository.save(cocktail8);
        cocktailInfoRepository.save(cocktail9);
        cocktailInfoRepository.save(cocktail10);
        cocktailInfoRepository.save(cocktail11);

        //when
        cocktailService.getRandomCocktailId();

        //then
        List<CocktailToday> todayCocktails = cocktailTodayRepository.findAll();

        System.out.println("===========test===========");
        for (CocktailToday cocktailToday : todayCocktails) {
            System.out.println(cocktailToday.getRandomId());
        }

    }

    private CocktailInfo createCocktail(Long id, String englishName, String koreanName, String description, String url1,
                                String url2, String url3, int level) {

        return CocktailInfo.builder()
                .cocktailInfoId(id)
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