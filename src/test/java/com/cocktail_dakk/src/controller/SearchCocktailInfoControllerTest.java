package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class SearchCocktailInfoControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    CocktailInfoRepository cocktailInfoRepository;

    @Test
    @Transactional
    public void findTest() throws Exception {

        // Given
        CocktailInfo cocktailInfo1=CocktailInfo.builder()
                .englishName("21st Century")
                .koreanName("21세기")
                .description("쓰다")
                .cocktailImageURL("1234")
                .cocktailBackgroundImageURL("5678")
                .recommendImageURL("91011")
                .smallNukkiImageURL("1234123")
                .alcoholLevel(1)
                .ingredient("크림 (15ml),드라이 진 (45ml)")
                .ratingAvg(new BigDecimal("4.0"))
                .status(Status.ACTIVE)
                .build();

        // When
        cocktailInfoRepository.save(cocktailInfo1);

        // Then
        mockMvc.perform(get("/search/cocktail/")
                        .param("page", "0")
                        .param("inputStr", " "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> jsonPath("$.result.content[0].englishName", is("21st Century")));
    }

    @Test
    @Transactional
    public void filterTest() throws Exception {

        // Given
        CocktailInfo cocktailInfo1=CocktailInfo.builder()
                .englishName("21st Century")
                .koreanName("21세기")
                .description("쓰다")
                .cocktailImageURL("1234")
                .cocktailBackgroundImageURL("5678")
                .recommendImageURL("91011")
                .smallNukkiImageURL("1234123")
                .alcoholLevel(1)
                .ingredient("크림 (15ml),드라이 진 (45ml)")
                .ratingAvg(new BigDecimal("4.0"))
                .status(Status.ACTIVE)
                .build();

        // When
        cocktailInfoRepository.save(cocktailInfo1);

        // Then
        mockMvc.perform(get("/search/cocktail/filter")
                .param("page", "0")
                .param("keywordName", "")
                .param("alcoholLevel", "1")
                .param("drinkName", ""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> jsonPath("$.result.content[0].englishName", is("21st Century")));
    }

}