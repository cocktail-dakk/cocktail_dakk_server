package com.cocktail_dakk.src.domain.user;

import com.cocktail_dakk.src.domain.Status;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "UserInfo")
public class UserInfo {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userInfoId;

    @Column(length = 20)
    @NotNull
    private String deviceNum;

    @Column(length = 20)
    @NotNull
    private String nickname;

    @NotNull
    private Integer age;

    @Column(length = 1)
    @NotNull
    private String sex;

    @NotNull
    private Integer alcoholLevel;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @OneToMany(mappedBy = "userInfo")
    private List<UserKeyword> userKeywords=new ArrayList<>();

    @OneToMany(mappedBy = "userInfo")
    private List<UserCocktail> userCocktails=new ArrayList<>();

    @OneToMany(mappedBy = "userInfo")
    private List<UserDrink> userDrinks=new ArrayList<>();

    @Builder
    public UserInfo(String deviceNum, String nickname, Integer age, String sex, Integer alcoholLevel, Status status){
        this.deviceNum=deviceNum;
        this.nickname=nickname;
        this.age=age;
        this.sex=sex;
        this.alcoholLevel= alcoholLevel;
        this.status=status;
    }
}
