package com.cocktail_dakk.src.service;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.BaseResponseStatus;
import com.cocktail_dakk.config.auth.dto.UserInfoDto;
import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.drink.Drink;
import com.cocktail_dakk.src.domain.drink.DrinkRepository;
import com.cocktail_dakk.src.domain.keyword.Keyword;
import com.cocktail_dakk.src.domain.keyword.KeywordRepository;
import com.cocktail_dakk.src.domain.user.Role;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.domain.user.UserInfoRepository;
import com.cocktail_dakk.src.domain.user.dto.UserInfoReq;
import com.cocktail_dakk.src.domain.user.dto.UserModifyReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Autowired
    UserInfoRepository userInfoRepository;

    @Test
    @Transactional
    public void modifyUserTest1() {
        try {
            save();
            createUser();

            UserInfoReq userInfoReq=new UserInfoReq("jjeong1", 22, "F", 0, "달달한", "데킬라");
            userInfoService.initUser(userInfoReq);

            UserModifyReq userModifyReq = UserModifyReq.builder()
                    .nickname("jjeong2")
                    .alcoholLevel(18)
                    .favouritesKeywords("깔끔한")
                    .favouritesDrinks("럼")
                    .build();
            userInfoService.modifyUser(userModifyReq);

            UserInfo userInfo = userInfoService.getUserInfo();

            assertThat(userInfo.getNickname()).isEqualTo(userModifyReq.getNickname());

        } catch(BaseException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void modifyUserTest2() {
        try {
            save();
            createUser();

            UserInfoReq userInfoReq=new UserInfoReq("jjeong1", 22, "F", 0, "달달한", "데킬라");
            userInfoService.initUser(userInfoReq);

            UserModifyReq userModifyReq = UserModifyReq.builder()
                    .nickname("jjeong2")
                    .alcoholLevel(18)
                    .favouritesKeywords("깔끔한")
                    .favouritesDrinks("")
                    .build();
            userInfoService.modifyUser(userModifyReq);

        } catch(BaseException e) {
            assertThat(BaseResponseStatus.POST_DRINK_EMPTY).isEqualTo(e.getStatus());
        }
    }

    @Test
    @Transactional
    public void modifyUserTest3() {
        try {
            save();
            createUser();

            UserInfoReq userInfoReq=new UserInfoReq("jjeong1", 22, "F", 0, "달달한", "데킬라");
            userInfoService.initUser(userInfoReq);

            UserModifyReq userModifyReq = UserModifyReq.builder()
                    .nickname("jjeong2")
                    .alcoholLevel(18)
                    .favouritesKeywords("")
                    .favouritesDrinks("럼")
                    .build();
            userInfoService.modifyUser(userModifyReq);

        } catch(BaseException e) {
            assertThat(BaseResponseStatus.POST_KEYWORD_EMPTY).isEqualTo(e.getStatus());
        }
    }

    private void save(){
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

        Drink drink2=Drink.builder()
                .drinkName("럼")
                .status(Status.ACTIVE)
                .build();

        drinkRepository.save(drink1);
        drinkRepository.save(drink2);
    }

    private void createUser(){
        UserInfo userInfo = UserInfo.builder()
                .email("test")
                .role(Role.USER)
                .build();

        userInfo.initUserInfo("jjeong", 22, "F", 0, Status.ACTIVE);

        UserInfo save = userInfoRepository.save(userInfo);

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .email(save.getEmail())
                .build();

        Authentication authentication = getAuthentication(userInfoDto);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Authentication getAuthentication(UserInfoDto member){
        return new UsernamePasswordAuthenticationToken(member, "", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

}