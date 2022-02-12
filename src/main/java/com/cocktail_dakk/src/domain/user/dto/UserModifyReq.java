package com.cocktail_dakk.src.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UserModifyReq {
    private String deviceNum;
    private String nickname;
    private Integer alcoholLevel;
    private String favouritesKeywords;
    private String favouritesDrinks;

    @Builder
    public UserModifyReq(String deviceNum,String nickname, Integer alcoholLevel, String favouritesKeywords, String favouritesDrinks){
        this.deviceNum = deviceNum;
        this.nickname = nickname;
        this.alcoholLevel = alcoholLevel;
        this.favouritesKeywords = favouritesKeywords;
        this.favouritesDrinks = favouritesDrinks;
    }
}
