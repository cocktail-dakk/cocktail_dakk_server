package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.config.auth.dto.UserInfoDto;
import com.cocktail_dakk.config.auth.jwt.TokenService;
import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.cocktail.*;
import com.cocktail_dakk.src.domain.drink.Drink;
import com.cocktail_dakk.src.domain.drink.DrinkRepository;
import com.cocktail_dakk.src.domain.keyword.Keyword;
import com.cocktail_dakk.src.domain.keyword.KeywordRepository;
import com.cocktail_dakk.src.domain.mixingMethod.MixingMethod;
import com.cocktail_dakk.src.domain.mixingMethod.MixingMethodRepository;
import com.cocktail_dakk.src.domain.user.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;

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

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserCocktailRepository userCocktailRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Test
    @WithMockUser(roles = "USER")
    @Transactional
    public void details() throws Exception{
        saveCocktailInfo();
//        Token token = createUser();
        Long cocktailInfoId = cocktailInfoRepository.findAll().get(0).getCocktailInfoId();
        saveUserInfo(cocktailInfoId);
        String userInfoId = userInfoRepository.findAll().get(0).getEmail();

        // Then
        mockMvc.perform(get("/cocktaildakk/v1/cocktails/details")
                        .with(request -> {
                            request.setScheme("http");
                            request.setServerName("localhost");
                            request.setServerPort(8080);

                            return request;
                        })
                        .param("id", cocktailInfoId.toString())
                        .param("email",userInfoId)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.cocktailInfoId").value(cocktailInfoId));

    }

    private Authentication getAuthentication(UserInfoDto member){
        return new UsernamePasswordAuthenticationToken(member, "", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    private UserInfo createUser(String email, String nickname, Integer age, String sex, Integer alcoholLevel, Status status){
        UserInfo userInfo = UserInfo.builder()
                .email(email)
                .role(Role.USER)
                .build();

        userInfo.initUserInfo(nickname, age, sex, alcoholLevel, status);

        return userInfo;
    }

    private void saveUserInfo(Long cocktailInfoId){
        UserInfo userInfo = createUser("test", "minnie",23,"F",14,Status.ACTIVE);
        UserInfo save = userInfoRepository.save(userInfo);

        CocktailInfo cocktailInfo = cocktailInfoRepository.findByCocktailInfoId(cocktailInfoId);

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .email(save.getEmail())
                .build();

        Authentication authentication = getAuthentication(userInfoDto);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //?????? ??????
        UserCocktail userCocktail = new UserCocktail(userInfo, cocktailInfo);
        userCocktailRepository.save(userCocktail);

        //?????? ??????
        //?????? ??? ?????? ????????? ????????? null??? ????????? ?????????
//        Rating rating = new Rating(cocktailInfo,userInfo,5);
//        ratingRepository.save(rating);

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

        MixingMethod mixingMethod1 = MixingMethod.builder()
                .mixingMethodName("?????????")
                .build();

        // ???????????? ?????? ???????????? ??????
        CocktailDrink cocktailDrink1=new CocktailDrink(cocktailInfo1, drink1);

        // ???????????? ?????? ????????? ???????????? ??????
        CocktailKeyword cocktailKeyword1=new CocktailKeyword(cocktailInfo1, keyword1);

        // ???????????? ?????? ??? ???????????? ??????
        CocktailMixingMethod cocktailMixingMethod1 = new CocktailMixingMethod(cocktailInfo1,mixingMethod1);

        // when ?????? ????????? ?????? ??????????????? ??????
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
