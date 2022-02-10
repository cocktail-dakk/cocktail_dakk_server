package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.cocktail.*;
import com.cocktail_dakk.src.domain.drink.Drink;
import com.cocktail_dakk.src.domain.drink.DrinkRepository;
import com.cocktail_dakk.src.domain.keyword.Keyword;
import com.cocktail_dakk.src.domain.keyword.KeywordRepository;
import com.cocktail_dakk.src.domain.mixingMethod.MixingMethod;
import com.cocktail_dakk.src.domain.mixingMethod.MixingMethodRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
public class CocktailDetailInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CocktailInfoRepository cocktailInfoRepository;

    @Autowired
    KeywordRepository keywordRepository;

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    MixingMethodRepository mixingMethodRepository;

    @Autowired
    CocktailKeywordRepository cocktailKeywordRepository;

    @Autowired
    CocktailDrinkRepository cocktailDrinkRepository;

    @Autowired
    CocktailMixingMethodRepository cocktailMixingMethodRepository;

    @Test
    @Transactional
    public void details() throws Exception{
        saveCocktailInfo();

        // Then
        mockMvc.perform(get("/cocktails/details")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.englishName").value("21st Century"));

    }


    private void saveCocktailInfo(){
        // Given
        CocktailInfo cocktailInfo1=CocktailInfo.builder()
                .englishName("21st Century")
                .koreanName("21세기")
                .description("쓰다")
                .cocktailImageURL("1234")
                .cocktailBackgroundImageURL("5678")
                .recommendImageURL("91011")
                .smallNukkiImageURL("1234123")
                .alcoholLevel(30)
                .ingredient("크림 (15ml),드라이 진 (45ml)")
                .ratingAvg(new BigDecimal("4.0"))
                .status(Status.ACTIVE)
                .build();

        Drink drink1=Drink.builder()
                .drinkName("데킬라")
                .status(Status.ACTIVE)
                .build();

        Drink drink2=Drink.builder()
                .drinkName("위스키")
                .status(Status.ACTIVE)
                .build();

        Keyword keyword1=Keyword.builder()
                .keywordName("깔끔한")
                .build();

        Keyword keyword2=Keyword.builder()
                .keywordName("달콤한")
                .build();

        MixingMethod mixingMethod1 = MixingMethod.builder()
                .mixingMethodName("쉐이킹")
                .build();

        // 칵테일과 기주 연관관계 설정
        CocktailDrink cocktailDrink1=new CocktailDrink(cocktailInfo1, drink1);

        // 칵테일과 취향 키워드 연관관계 설정
        CocktailKeyword cocktailKeyword1=new CocktailKeyword(cocktailInfo1, keyword1);

        // 칵테일과 섞는 법 연관관계 설정
        CocktailMixingMethod cocktailMixingMethod1 = new CocktailMixingMethod(cocktailInfo1,mixingMethod1);

        // when 조인 엔티티 직접 영속화하는 방법
        cocktailInfoRepository.save(cocktailInfo1);

        drinkRepository.save(drink1);
        drinkRepository.save(drink2);

        cocktailDrinkRepository.save(cocktailDrink1);

        keywordRepository.save(keyword1);
        keywordRepository.save(keyword2);

        cocktailKeywordRepository.save(cocktailKeyword1);

        mixingMethodRepository.save(mixingMethod1);

        cocktailMixingMethodRepository.save(cocktailMixingMethod1);
    }




}
