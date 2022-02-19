//package com.cocktail_dakk.src.service;
//
//import com.cocktail_dakk.config.BaseException;
//import com.cocktail_dakk.config.BaseResponseStatus;
//import com.cocktail_dakk.src.domain.Status;
//import com.cocktail_dakk.src.domain.drink.Drink;
//import com.cocktail_dakk.src.domain.drink.DrinkRepository;
//import com.cocktail_dakk.src.domain.keyword.Keyword;
//import com.cocktail_dakk.src.domain.keyword.KeywordRepository;
//import com.cocktail_dakk.src.domain.user.UserInfo;
//import com.cocktail_dakk.src.domain.user.dto.UserInfoReq;
//import com.cocktail_dakk.src.domain.user.dto.UserModifyReq;
//import com.cocktail_dakk.src.domain.user.dto.UserSignUpReq;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@ActiveProfiles("test")
//@TestPropertySource(locations = "classpath:application-test.properties")
//class UserInfoServiceTest {
//
//    @Autowired
//    KeywordRepository keywordRepository;
//
//    @Autowired
//    DrinkRepository drinkRepository;
//
//    @Autowired
//    UserInfoService userInfoService;
//
//    @Test
//    @Transactional
//    public void duplicateSignUpTest() {
//        try {
//            save();
//
//            UserInfoReq userInfo=UserInfoReq.builder()
//                    .deviceNum("1234")
//                    .nickname("dale")
//                    .age(25)
//                    .sex("m")
//                    .alcoholLevel(3)
//                    .build();
//
//            UserSignUpReq userSignUpReq=new UserSignUpReq(userInfo, "깔끔한,달달한", "데킬라");
//
//            userInfoService.signUpUser(userSignUpReq);
//
//            userInfoService.signUpUser(userSignUpReq);
//        }catch (BaseException e){
//            Assertions.assertEquals(BaseResponseStatus.EXIST_USER, e.getStatus());
//        }
//    }
//
//    @Test
//    @Transactional
//    public void modifyUserTest1() {
//        try {
//            save();
//
//            UserInfoReq userInfo = UserInfoReq.builder()
//                    .deviceNum("0130")
//                    .nickname("jjeong1")
//                    .age(22)
//                    .sex("F")
//                    .alcoholLevel(0)
//                    .build();
//
//            UserModifyReq userModifyReq = UserModifyReq.builder()
//                    .deviceNum("0130")
//                    .nickname("jjeong2")
//                    .alcoholLevel(18)
//                    .favouritesKeywords("깔끔한")
//                    .favouritesDrinks("럼")
//                    .build();
//
//            UserSignUpReq userSignUpReq= new UserSignUpReq(userInfo,"달달한","데킬라");
//
//            userInfoService.signUpUser(userSignUpReq);
//            userInfoService.modifyUser(userModifyReq);
//
//        } catch(BaseException e) {
//            Assertions.assertEquals(BaseResponseStatus.POST_DRINK_EMPTY, e.getStatus());
//            //Assertions.assertEquals(BaseResponseStatus.POST_KEYWORD_EMPTY,e.getStatus());
//        }
//    }
//
//    @Test
//    @Transactional
//    public void modifyUserTest2() {
//        try {
//            save();
//
//            UserInfoReq userInfo = UserInfoReq.builder()
//                    .deviceNum("0130")
//                    .nickname("jjeong1")
//                    .age(22)
//                    .sex("F")
//                    .alcoholLevel(0)
//                    .build();
//
//            UserModifyReq userModifyReq = UserModifyReq.builder()
//                    .deviceNum("0130")
//                    .nickname("jjeong2")
//                    .alcoholLevel(18)
//                    .favouritesKeywords("깔끔한")
//                    .favouritesDrinks("")
//                    .build();
//
//            UserSignUpReq userSignUpReq= new UserSignUpReq(userInfo,"달달한","데킬라");
//
//            userInfoService.signUpUser(userSignUpReq);
//            userInfoService.modifyUser(userModifyReq);
//
//        } catch(BaseException e) {
//            Assertions.assertEquals(BaseResponseStatus.POST_DRINK_EMPTY, e.getStatus());
//        }
//    }
//
//    @Test
//    @Transactional
//    public void modifyUserTest3() {
//        try {
//            save();
//
//            UserInfoReq userInfo = UserInfoReq.builder()
//                    .deviceNum("0130")
//                    .nickname("jjeong1")
//                    .age(22)
//                    .sex("F")
//                    .alcoholLevel(0)
//                    .build();
//
//            UserModifyReq userModifyReq = UserModifyReq.builder()
//                    .deviceNum("0130")
//                    .nickname("jjeong2")
//                    .alcoholLevel(18)
//                    .favouritesKeywords("")
//                    .favouritesDrinks("럼")
//                    .build();
//
//            UserSignUpReq userSignUpReq= new UserSignUpReq(userInfo,"달달한","데킬라");
//
//            userInfoService.signUpUser(userSignUpReq);
//            userInfoService.modifyUser(userModifyReq);
//
//        } catch(BaseException e) {
//            Assertions.assertEquals(BaseResponseStatus.POST_KEYWORD_EMPTY,e.getStatus());
//        }
//    }
//
//    private void save(){
//        Keyword keyword1=Keyword.builder()
//                .keywordName("깔끔한")
//                .build();
//
//        Keyword keyword2=Keyword.builder()
//                .keywordName("달달한")
//                .build();
//
//        keywordRepository.save(keyword1);
//        keywordRepository.save(keyword2);
//
//        Drink drink1=Drink.builder()
//                .drinkName("데킬라")
//                .status(Status.ACTIVE)
//                .build();
//
//        Drink drink2=Drink.builder()
//                .drinkName("럼")
//                .status(Status.ACTIVE)
//                .build();
//
//        drinkRepository.save(drink1);
//        drinkRepository.save(drink2);
//    }
//
//}