package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.cocktail.*;
import com.cocktail_dakk.src.domain.drink.Drink;
import com.cocktail_dakk.src.domain.drink.DrinkRepository;
import com.cocktail_dakk.src.domain.keyword.Keyword;
import com.cocktail_dakk.src.domain.keyword.KeywordRepository;
import com.cocktail_dakk.src.domain.user.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class SearchCocktailInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CocktailInfoRepository cocktailInfoRepository;

    @Autowired
    KeywordRepository keywordRepository;

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    CocktailKeywordRepository cocktailKeywordRepository;

    @Autowired
    CocktailDrinkRepository cocktailDrinkRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Test
    @WithMockUser(roles = "USER")
    @Transactional
    public void findTest() throws Exception {
        saveCocktailInfo();
        // Then
        mockMvc.perform(get("/cocktaildakk/v1/search/cocktail/")
                        .with(request -> {
                            request.setScheme("http");
                            request.setServerName("localhost");
                            request.setServerPort(8080);

                            return request;
                        })
                        .param("page", "0")
                        .param("inputStr", "21??????"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.content[0].englishName").value("21st Century"));
    }

    @Test
    @WithMockUser(roles = "USER")
    @Transactional
    public void filterTest() throws Exception {
        saveCocktailInfo();

        // Then
        mockMvc.perform(get("/cocktaildakk/v1/search/cocktail/filter")
                .with(request -> {
                    request.setScheme("http");
                    request.setServerName("localhost");
                    request.setServerPort(8080);

                    return request;
                })
                .param("page", "0")
                .param("keywordName", "")
                .param("minAlcoholLevel", "1")
                .param("maxAlcoholLevel", "10")
                .param("drinkName", ""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.content[0].englishName").value("God Father"));
    }

    private void saveCocktailInfo(){
        // Given
        CocktailInfo cocktailInfo1=CocktailInfo.builder()
                .englishName("21st Century")
                .koreanName("21??????")
                .description("??????")
                .cocktailImageURL("1234")
                .cocktailBackgroundImageURL("5678")
                .recommendImageURL("91011")
                .smallNukkiImageURL("1234123")
                .alcoholLevel(30)
                .ingredient("?????? (15ml),????????? ??? (45ml)")
                .ratingAvg(new BigDecimal("4.0"))
                .status(Status.ACTIVE)
                .build();

        CocktailInfo cocktailInfo2=CocktailInfo.builder()
                .englishName("God Father")
                .koreanName("??? ??????")
                .description("????????????")
                .cocktailImageURL("abcd")
                .cocktailBackgroundImageURL("efg")
                .recommendImageURL("hijk")
                .smallNukkiImageURL("1234123")
                .alcoholLevel(1)
                .ingredient("?????? (15ml),????????? ??? (45ml)")
                .ratingAvg(new BigDecimal("4.0"))
                .status(Status.ACTIVE)
                .build();

        CocktailInfo cocktailInfo3=CocktailInfo.builder()
                .englishName("Gold Rush")
                .koreanName("?????? ??????")
                .description("??? ?????????")
                .cocktailImageURL("lmno")
                .cocktailBackgroundImageURL("pqkr")
                .recommendImageURL("stu")
                .smallNukkiImageURL("1234123")
                .alcoholLevel(1)
                .ingredient("?????? (15ml),????????? ??? (45ml)")
                .ratingAvg(new BigDecimal("4.0"))
                .status(Status.ACTIVE)
                .build();

        Drink drink1=Drink.builder()
                .drinkName("?????????")
                .status(Status.ACTIVE)
                .build();

        Drink drink2=Drink.builder()
                .drinkName("?????????")
                .status(Status.ACTIVE)
                .build();

        Keyword keyword1=Keyword.builder()
                .keywordName("?????????")
                .build();

        Keyword keyword2=Keyword.builder()
                .keywordName("?????????")
                .build();

        // ???????????? ?????? ???????????? ??????
        CocktailDrink cocktailDrink1=new CocktailDrink(cocktailInfo1, drink1);
        CocktailDrink cocktailDrink2=new CocktailDrink(cocktailInfo2, drink2);
        CocktailDrink cocktailDrink3=new CocktailDrink(cocktailInfo3, drink2);
        CocktailDrink cocktailDrink4=new CocktailDrink(cocktailInfo2, drink1);

        // ???????????? ?????? ????????? ???????????? ??????
        CocktailKeyword cocktailKeyword1=new CocktailKeyword(cocktailInfo1, keyword1);
        CocktailKeyword cocktailKeyword2=new CocktailKeyword(cocktailInfo2, keyword2);
        CocktailKeyword cocktailKeyword3=new CocktailKeyword(cocktailInfo3, keyword1);

        // when ?????? ????????? ?????? ??????????????? ??????
        cocktailInfoRepository.save(cocktailInfo1);
        cocktailInfoRepository.save(cocktailInfo2);
        cocktailInfoRepository.save(cocktailInfo3);

        drinkRepository.save(drink1);
        drinkRepository.save(drink2);

        cocktailDrinkRepository.save(cocktailDrink1);
        cocktailDrinkRepository.save(cocktailDrink2);
        cocktailDrinkRepository.save(cocktailDrink3);
        cocktailDrinkRepository.save(cocktailDrink4);

        keywordRepository.save(keyword1);
        keywordRepository.save(keyword2);

        cocktailKeywordRepository.save(cocktailKeyword1);
        cocktailKeywordRepository.save(cocktailKeyword2);
        cocktailKeywordRepository.save(cocktailKeyword3);
    }

}