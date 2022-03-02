package com.cocktail_dakk.src.domain.user;

import com.cocktail_dakk.src.domain.keyword.Keyword;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "UserKeyword")
public class UserKeyword {

    public UserKeyword(UserInfo userInfo, Keyword keyword) {
        this.userInfo=userInfo;
        this.keyword = keyword;
        userInfo.getUserKeywords().add(this);
    }

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userKeywordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userInfoId")
    private UserInfo userInfo;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "keywordId")
    private Keyword keyword;
}
