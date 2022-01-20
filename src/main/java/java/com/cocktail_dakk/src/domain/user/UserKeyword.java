package java.com.cocktail_dakk.src.domain.user;

import lombok.Getter;

import javax.persistence.*;
import java.com.cocktail_dakk.src.domain.Keyword;

@Entity
@Getter
public class UserKeyword {

    public UserKeyword() {
    }

    public UserKeyword(UserInfo userInfo, Keyword keyword) {
        this.userInfo = userInfo;
        this.keyword = keyword;
    }

    @Id @GeneratedValue
    private Long userKeywordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userInfoId")
    private UserInfo userInfo;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "kewordId")
    private Keyword keyword;


}
