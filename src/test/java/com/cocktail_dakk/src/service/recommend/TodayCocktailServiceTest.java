package com.cocktail_dakk.src.service.recommend;

import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.cocktail.*;
import com.cocktail_dakk.src.domain.drink.DrinkRepository;
import com.cocktail_dakk.src.domain.keyword.Keyword;
import com.cocktail_dakk.src.domain.keyword.KeywordRepository;
import com.cocktail_dakk.src.domain.user.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
class TodayCocktailServiceTest {
    @Autowired
    private TodayCocktailService todayCocktailService;
    @Autowired
    private CocktailTodayRepository cocktailTodayRepository;

    @BeforeAll
    private static void beforeAll(@Autowired CocktailInfoRepository cocktailInfoRepository,
                                  @Autowired CocktailKeywordRepository cocktailKeywordRepository, @Autowired KeywordRepository keywordRepository) {
        CocktailInfo cocktailInfo1 = createCocktail("Golden Dream", "골든 드림", "달콤하고 부드러운 맛 덕분에 주로 식후주로 사용되며, 이전 IBA 공식 칵테일에서도 식후주로 분류된 바 있다.",
                "url111", "url222", "url333", 2, Status.ACTIVE);
        CocktailInfo cocktailInfo2 = createCocktail("cocktailInfo2", "칵테일정보2", "두 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 5, Status.INACTIVE);
        CocktailInfo cocktailInfo3 = createCocktail("cocktailInfo3", "칵테일정보3", "세 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 7, Status.INACTIVE);
        CocktailInfo cocktailInfo4 = createCocktail("cocktailInfo4", "칵테일정보4", "네 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 10, Status.INACTIVE);
        CocktailInfo cocktailInfo5 = createCocktail("cocktailInfo5", "칵테일정보5", "다섯 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 4, Status.ACTIVE);
        CocktailInfo cocktailInfo6 = createCocktail("cocktailInfo6", "칵테일정보6", "여섯 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 2, Status.ACTIVE);
        CocktailInfo cocktailInfo7 = createCocktail("cocktailInfo7", "칵테일정보7", "여섯 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 2, Status.ACTIVE);
        CocktailInfo cocktailInfo8 = createCocktail("cocktailInfo8", "칵테일정보8", "여섯 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 2, Status.ACTIVE);
        CocktailInfo cocktailInfo9 = createCocktail("cocktailInfo9", "칵테일정보9", "여섯 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 2, Status.ACTIVE);
        CocktailInfo cocktailInfo10 = createCocktail("cocktailInfo10", "칵테일정보10", "여섯 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 2, Status.ACTIVE);

        cocktailInfoRepository.save(cocktailInfo1);
        cocktailInfoRepository.save(cocktailInfo2);
        cocktailInfoRepository.save(cocktailInfo3);
        cocktailInfoRepository.save(cocktailInfo4);
        cocktailInfoRepository.save(cocktailInfo5);
        cocktailInfoRepository.save(cocktailInfo6);
        cocktailInfoRepository.save(cocktailInfo7);
        cocktailInfoRepository.save(cocktailInfo8);
        cocktailInfoRepository.save(cocktailInfo9);
        cocktailInfoRepository.save(cocktailInfo10);

        Keyword keyword1=Keyword.builder()
                .keywordName("깔끔한")
                .build();

        Keyword keyword2=Keyword.builder()
                .keywordName("달콤한")
                .build();

        keywordRepository.save(keyword1);
        keywordRepository.save(keyword2);

        CocktailKeyword cocktailKeyword1=new CocktailKeyword(cocktailInfo1, keyword1);
        CocktailKeyword cocktailKeyword2=new CocktailKeyword(cocktailInfo2, keyword2);
        CocktailKeyword cocktailKeyword3=new CocktailKeyword(cocktailInfo3, keyword1);
        CocktailKeyword cocktailKeyword4=new CocktailKeyword(cocktailInfo4, keyword2);
        CocktailKeyword cocktailKeyword5=new CocktailKeyword(cocktailInfo5, keyword1);
        CocktailKeyword cocktailKeyword6=new CocktailKeyword(cocktailInfo6, keyword2);
        CocktailKeyword cocktailKeyword7=new CocktailKeyword(cocktailInfo7, keyword1);
        CocktailKeyword cocktailKeyword8=new CocktailKeyword(cocktailInfo8, keyword2);
        CocktailKeyword cocktailKeyword9=new CocktailKeyword(cocktailInfo9, keyword1);
        CocktailKeyword cocktailKeyword10=new CocktailKeyword(cocktailInfo10, keyword2);

        cocktailKeywordRepository.save(cocktailKeyword1);
        cocktailKeywordRepository.save(cocktailKeyword2);
        cocktailKeywordRepository.save(cocktailKeyword3);
        cocktailKeywordRepository.save(cocktailKeyword4);
        cocktailKeywordRepository.save(cocktailKeyword5);
        cocktailKeywordRepository.save(cocktailKeyword6);
        cocktailKeywordRepository.save(cocktailKeyword7);
        cocktailKeywordRepository.save(cocktailKeyword8);
        cocktailKeywordRepository.save(cocktailKeyword9);
        cocktailKeywordRepository.save(cocktailKeyword10);

    }

    @AfterAll
    private static void afterAll(@Autowired CocktailInfoRepository cocktailInfoRepository,
                                 @Autowired KeywordRepository keywordRepository, @Autowired CocktailKeywordRepository cocktailKeywordRepository){
        System.out.println("afterAll");
        cocktailKeywordRepository.deleteAll();
        keywordRepository.deleteAll();
        cocktailInfoRepository.deleteAll();
    }


    @Test
    @Transactional
    @DisplayName("랜덤 칵테일을 추출한다.")
    public void cocktailToday() throws Exception{

        //when
        todayCocktailService.getRandomCocktailId();

        //then
        List<CocktailToday> todayCocktails = cocktailTodayRepository.findAll();

        System.out.println("===========test===========");
        for (CocktailToday cocktailToday : todayCocktails) {
            System.out.println(cocktailToday.getRandomId());
        }
        assertThat(todayCocktails.get(0).getRandomId().size()).isEqualTo(5);

    }

    private static CocktailInfo createCocktail(String englishName, String koreanName, String description, String url1,
                                               String url2, String url3, int level, Status status) {

        return CocktailInfo.builder()
                .englishName(englishName)
                .koreanName(koreanName)
                .description(description)
                .cocktailImageURL(url1)
                .cocktailBackgroundImageURL(url2)
                .recommendImageURL(url3)
                .alcoholLevel(level)
                .status(status)
                .build();
    }

}