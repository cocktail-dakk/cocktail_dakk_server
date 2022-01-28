package com.cocktail_dakk.src.domain.user.dto;

import com.cocktail_dakk.src.domain.user.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class UserInfoRes {
    private Long id;
    private String deviceNum;
    private String nickname;
    private Integer age;
    private String sex;
    private Integer alcoholLevel;
    // 취향키워드를 받는다고,,,리스트로 받고 전달하기 // 질문하기,,, 나만 어렵나 ㅎ
    private ArrayList<String> favourite = new ArrayList<String>();

    public UserInfoRes(UserInfo userInfo){
        this.id = userInfo.getUserInfoId();
        this.deviceNum = userInfo.getDeviceNum();
        this.nickname = userInfo.getNickname();
        this.age = userInfo.getAge();
        this.sex = userInfo.getSex();
        this.alcoholLevel = userInfo.getAlcoholLevel();
    }
}
