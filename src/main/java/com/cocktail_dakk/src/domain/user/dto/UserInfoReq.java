package com.cocktail_dakk.src.domain.user.dto;


import com.cocktail_dakk.src.domain.user.UserInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoReq {
    private String nickname;
    private Integer age;
    private String sex;
    private Integer alcoholLevel;
    private String favouritesKeywords;
    private String favouritesDrinks;

    @Builder
    public UserInfoReq(String nickname, Integer age, String sex, Integer alcoholLevel, String favouritesKeywords, String favouritesDrinks) {
        this.nickname = nickname;
        this.age = age;
        this.sex = sex;
        this.alcoholLevel = alcoholLevel;
        this.favouritesKeywords=favouritesKeywords;
        this.favouritesDrinks=favouritesDrinks;
    }
}
