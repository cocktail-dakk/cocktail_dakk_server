package com.cocktail_dakk.src.service.recommend;

import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.cocktail.*;
import com.cocktail_dakk.src.domain.cocktail.dto.GetUserRecommendationRes;
import com.cocktail_dakk.src.domain.cocktail.dto.UserRecommendationList;
import com.cocktail_dakk.src.domain.drink.Drink;
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

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class UserCocktailServiceTest {

    @Autowired
    private UserCocktailService userCocktailService;
    @Autowired
    private UserInfoRepository userInfoRepository;

    @BeforeAll
    private static void beforeAll(@Autowired CocktailInfoRepository cocktailInfoRepository,
                                  @Autowired UserInfoRepository userInfoRepository,
                                  @Autowired UserKeywordRepository userKeywordRepository,
                                  @Autowired KeywordRepository keywordRepository,
                                  @Autowired DrinkRepository drinkRepository,
                                  @Autowired CocktailDrinkRepository cocktailDrinkRepository,
                                  @Autowired CocktailKeywordRepository cocktailKeywordRepository,
                                  @Autowired UserDrinkRepository userDrinkRepository) {

        //?????????
        CocktailInfo cocktailInfo1 = createCocktail("Golden Dream", "?????? ??????", "???????????? ???????????? ??? ????????? ?????? ???????????? ????????????, ?????? IBA ?????? ?????????????????? ???????????? ????????? ??? ??????.",
                "url111", "url222", "url333", 2, Status.ACTIVE);
        CocktailInfo cocktailInfo2 = createCocktail("Woo Woo", "??????", "??? ?????? ???????????? ????????? ?????????",
                "url222", "url2222", "url22222", 14, Status.ACTIVE);
        CocktailInfo cocktailInfo3 = createCocktail("Trinidad Sour", "??????????????? ??????", "??? ?????? ???????????? ????????? ?????????",
                "url222", "url2222", "url22222", 10, Status.INACTIVE);

        cocktailInfoRepository.save(cocktailInfo1);
        cocktailInfoRepository.save(cocktailInfo2);
        cocktailInfoRepository.save(cocktailInfo3);

        //????????? ??????
        Drink drink1 = Drink.builder()
                .drinkName("?????????")
                .build();

        Drink drink2 = Drink.builder()
                .drinkName("?????????")
                .build();

        drinkRepository.save(drink1);
        drinkRepository.save(drink2);


        CocktailDrink cocktailDrink1 = new CocktailDrink(cocktailInfo2, drink1);
        CocktailDrink cocktailDrink2 = new CocktailDrink(cocktailInfo1, drink2);
        CocktailDrink cocktailDrink3 = new CocktailDrink(cocktailInfo3, drink1);

        cocktailDrinkRepository.save(cocktailDrink1);
        cocktailDrinkRepository.save(cocktailDrink2);
        cocktailDrinkRepository.save(cocktailDrink3);

        //????????? ?????????
        Keyword keyword=Keyword.builder()
                .keywordName("?????????")
                .build();
        keywordRepository.save(keyword);

        CocktailKeyword cocktailKeyword1 = new CocktailKeyword(cocktailInfo1, keyword);
        CocktailKeyword cocktailKeyword2 = new CocktailKeyword(cocktailInfo2, keyword);
        CocktailKeyword cocktailKeyword3 = new CocktailKeyword(cocktailInfo3, keyword);

        cocktailKeywordRepository.save(cocktailKeyword1);
        cocktailKeywordRepository.save(cocktailKeyword2);
        cocktailKeywordRepository.save(cocktailKeyword3);

        //??????
        UserInfo userInfo = createUser("test", "minnie",23,"F",14,Status.ACTIVE);
        userInfoRepository.save(userInfo);

        //?????? ?????????
        UserKeyword userKeyword=new UserKeyword(userInfo, keyword);
        userKeywordRepository.save(userKeyword);
        userInfoRepository.flush();

        //?????? ??????
        UserDrink userDrink = new UserDrink(userInfo, drink1);
        userDrinkRepository.save(userDrink);

    }

    @AfterAll
    private static void afterAll(@Autowired CocktailInfoRepository cocktailInfoRepository,
                                 @Autowired UserInfoRepository userInfoRepository,
                                 @Autowired UserKeywordRepository userKeywordRepository,
                                 @Autowired KeywordRepository keywordRepository,
                                 @Autowired DrinkRepository drinkRepository,
                                 @Autowired CocktailDrinkRepository cocktailDrinkRepository,
                                 @Autowired CocktailKeywordRepository cocktailKeywordRepository,
                                 @Autowired UserDrinkRepository userDrinkRepository){
        System.out.println("afterAll");
        userKeywordRepository.deleteAll();
        cocktailDrinkRepository.deleteAll();
        cocktailKeywordRepository.deleteAll();
        userDrinkRepository.deleteAll();

        cocktailInfoRepository.deleteAll();
        userInfoRepository.deleteAll();
        keywordRepository.deleteAll();
        drinkRepository.deleteAll();
    }

    @Test
    @DisplayName("??????, ??????, ???????????? ???????????? ???????????? ????????????.")
    @Transactional
    public void checkList() throws Exception{
        GetUserRecommendationRes userRecommendCocktail = userCocktailService.getUserRecommendCocktail(userInfoRepository.findByEmail("test").get());
        System.out.println("====?????? ??????====");
        List<UserRecommendationList> userRecommendationLists = userRecommendCocktail.getUserRecommendationLists();

        for (UserRecommendationList userRecommendationList : userRecommendationLists) {
            System.out.println("=======");
            System.out.println(userRecommendationList.getKoreanName());
        }
        assertThat(userRecommendCocktail.getUserRecommendationLists().get(0).getKoreanName()).isEqualTo("??????");
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

    private static UserInfo createUser(String email, String nickname, Integer age, String sex, Integer alcoholLevel, Status status){
        UserInfo userInfo = UserInfo.builder()
                .email(email)
                .role(Role.USER)
                .build();

        userInfo.initUserInfo(nickname, age, sex, alcoholLevel, status);

        return userInfo;
    }

}