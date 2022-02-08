package com.cocktail_dakk.src.service;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.BaseResponseStatus;
import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.drink.Drink;
import com.cocktail_dakk.src.domain.drink.DrinkRepository;
import com.cocktail_dakk.src.domain.keyword.Keyword;
import com.cocktail_dakk.src.domain.keyword.KeywordRepository;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.domain.user.dto.UserInfoReq;
import com.cocktail_dakk.src.domain.user.dto.UserSignUpReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class UserInfoServiceTest {

    @Autowired
    KeywordRepository keywordRepository;

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    UserInfoService userInfoService;

    @Test
    @Transactional
    public void duplicateSignUpTest() {
        try {
            Keyword keyword1=Keyword.builder()
                    .keywordName("깔끔한")
                    .build();

            Keyword keyword2=Keyword.builder()
                    .keywordName("달달한")
                    .build();

            keywordRepository.save(keyword1);
            keywordRepository.save(keyword2);

            Drink drink1=Drink.builder()
                    .drinkName("데킬라")
                    .status(Status.ACTIVE)
                    .build();

            drinkRepository.save(drink1);


            UserInfoReq userInfo=UserInfoReq.builder()
                    .deviceNum("1234")
                    .nickname("dale")
                    .age(25)
                    .sex("m")
                    .alcoholLevel(3)
                    .build();

            UserSignUpReq userSignUpReq=new UserSignUpReq(userInfo, "깔끔한,달달한", "데킬라");

            userInfoService.signUpUser(userSignUpReq);

            userInfoService.signUpUser(userSignUpReq);
        }catch (BaseException e){
            Assertions.assertEquals(BaseResponseStatus.EXIST_USER, e.getStatus());
        }
    }

}