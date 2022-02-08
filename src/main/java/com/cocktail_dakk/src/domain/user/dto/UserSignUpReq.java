package com.cocktail_dakk.src.domain.user.dto;

import com.cocktail_dakk.src.domain.keyword.Keyword;
import com.cocktail_dakk.src.domain.keyword.dto.KeywordRes;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.domain.user.UserKeyword;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class UserSignUpReq {
    private String deviceNum;
    private String nickname;
    private Integer age;
    private String sex;
    private Integer alcoholLevel;
    private String favouritesKeywords;
    private String favouritesDrinks;

    public UserSignUpReq(UserInfoReq userInfoReq, String favouritesKeywords, String favouritesDrinks){
        this.deviceNum = userInfoReq.getDeviceNum();
        this.nickname = userInfoReq.getNickname();
        this.age = userInfoReq.getAge();
        this.sex = userInfoReq.getSex();
        this.alcoholLevel = userInfoReq.getAlcoholLevel();
        this.favouritesKeywords = favouritesKeywords;
        this.favouritesDrinks = favouritesDrinks;
    }
}
