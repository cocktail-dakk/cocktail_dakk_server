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

    @NotNull
    private String email;

    @Column(length = 20)
    private String nickname;

    private Integer age;

    @Column(length = 1)
    private String sex;

    private Integer alcoholLevel;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @OneToMany(mappedBy = "userInfo")
    private List<UserKeyword> userKeywords=new ArrayList<>();

    @OneToMany(mappedBy = "userInfo")
    private List<UserCocktail> userCocktails=new ArrayList<>();

    @OneToMany(mappedBy = "userInfo")
    private List<UserDrink> userDrinks=new ArrayList<>();

    @Builder
    public UserInfo(String email, Role role){
        this.email=email;
        this.role=role;
    }

    public void initUserInfo(String nickname, Integer age, String sex, Integer alcoholLevel, Status status){
        this.nickname=nickname;
        this.age=age;
        this.sex=sex;
        this.alcoholLevel= alcoholLevel;
        this.status=status;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

    public void updateUser(String nickname, Integer alcoholLevel){
        this.nickname = nickname;
        this.alcoholLevel = alcoholLevel;
    }
}
