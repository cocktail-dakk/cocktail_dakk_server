package com.cocktail_dakk.src.domain.user.dto;

import com.cocktail_dakk.src.domain.drink.dto.DrinkRes;
import com.cocktail_dakk.src.domain.keyword.dto.KeywordRes;
import com.cocktail_dakk.src.domain.user.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class UserInfoRes {
    private Long id;
    private String email;
    private String nickname;
    private Integer age;
    private String sex;
    private Integer alcoholLevel;
    private List<KeywordRes> userKeywords;
    private List<DrinkRes> userDrinks;

    public UserInfoRes(UserInfo userInfo){
        this.id = userInfo.getUserInfoId();
        this.email=userInfo.getEmail();
        this.nickname = userInfo.getNickname();
        this.age = userInfo.getAge();
        this.sex = userInfo.getSex();
        this.alcoholLevel = userInfo.getAlcoholLevel();
        this.userKeywords = userInfo.getUserKeywords()
                .stream()
                .map(KeywordRes::new)
                .collect(Collectors.toList());
        this.userDrinks = userInfo.getUserDrinks()
                .stream()
                .map(DrinkRes::new)
                .collect(Collectors.toList());
    }
}
