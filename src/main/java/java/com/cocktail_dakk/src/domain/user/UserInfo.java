package java.com.cocktail_dakk.src.domain.user;

import javax.persistence.*;
import java.com.cocktail_dakk.src.domain.Status;
import java.util.ArrayList;
import java.util.List;

@Entity
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

    @OneToMany(mappedBy = "userInfo")
    private List<UserKeyword> userKeywords=new ArrayList<>();

    @OneToMany(mappedBy = "userInfo")
    private List<UserCocktail> userCocktails=new ArrayList<>();


}
