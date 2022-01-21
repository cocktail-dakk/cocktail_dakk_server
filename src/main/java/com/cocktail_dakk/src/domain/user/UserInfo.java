package com.cocktail_dakk.src.domain.user;

import com.cocktail_dakk.src.domain.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class UserInfo {

    @Id @GeneratedValue
    private Long userInfoId;

    @Column(length = 20)
    private String deviceNum;

    @Column(length = 20)
    private String nickname;

    private Integer age;

    @Column(length = 1)
    private String sex;

    private Integer alcoholLevel;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "userInfo"/*, cascade = CascadeType.ALL*/)
    private List<UserKeyword> userKeywords=new ArrayList<>();

    @OneToMany(mappedBy = "userInfo"/*, cascade = CascadeType.ALL*/)
    private List<UserCocktail> userCocktails=new ArrayList<>();

    @OneToMany(mappedBy = "userInfo"/*, cascade = CascadeType.ALL*/)
    private List<UserDrink> userDrinks=new ArrayList<>();

    @Builder
    public UserInfo(String deviceNum, String nickname, Integer age, String sex, Integer alcoholLevel){
        this.deviceNum=deviceNum;
        this.nickname=nickname;
        this.age=age;
        this.sex=sex;
        this.alcoholLevel= alcoholLevel;
    }
}
