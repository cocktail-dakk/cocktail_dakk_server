package com.cocktail_dakk.src.domain.user.dto;


import com.cocktail_dakk.src.domain.user.UserInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoReq {
    private String deviceNum;
    private String nickname;
    private Integer age;
    private String sex;
    private Integer alcoholLevel;

    @Builder
    public UserInfoReq(String deviceNum, String nickname, Integer age, String sex, Integer alcoholLevel) {
        this.deviceNum = deviceNum;
        this.nickname = nickname;
        this.age = age;
        this.sex = sex;
        this.alcoholLevel = alcoholLevel;
    }
}
