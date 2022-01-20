package java.com.cocktail_dakk.src.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.com.cocktail_dakk.src.domain.keyword.Keyword;

@Entity
@NoArgsConstructor
@Getter
public class UserKeyword {

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
