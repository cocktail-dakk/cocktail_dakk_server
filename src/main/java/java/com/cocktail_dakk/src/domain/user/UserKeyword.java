package java.com.cocktail_dakk.src.domain.user;

import javax.persistence.*;
import java.com.cocktail_dakk.src.domain.Keyword;

@Entity
public class UserKeyword {

    @Id @GeneratedValue
    private Long userKeywordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userInfoId")
    private UserInfo userInfo;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "kewordId")
    private Keyword keyword;
}
