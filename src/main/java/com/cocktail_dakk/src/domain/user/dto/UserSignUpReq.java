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
    private Long id;
    private String deviceNum;
    private String nickname;
    private Integer age;
    private String sex;
    private Integer alcoholLevel;
    private String favouritesKeywords;
    private String favouritesDrinks;

    public UserSignUpReq(UserInfo userInfo, String favouritesKeywords, String favouritesDrinks){
        this.id = userInfo.getUserInfoId();
        this.deviceNum = userInfo.getDeviceNum();
        this.nickname = userInfo.getNickname();
        this.age = userInfo.getAge();
        this.sex = userInfo.getSex();
        this.alcoholLevel = userInfo.getAlcoholLevel();
        this.favouritesKeywords = favouritesKeywords;
        this.favouritesDrinks = favouritesDrinks;
    }
}
