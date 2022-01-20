package com.cocktail_dakk.src.domain.user;

import com.cocktail_dakk.src.domain.keyword.Keyword;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cocktail_dakk.src.domain.keyword.KeywordRepository;

@SpringBootTest
class UserKeywordTest {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    KeywordRepository keywordRepository;

    @Test
    public void createUserKeyword(){
        // Given
        String keywordName="깔끔한";

        Keyword keyword=Keyword.builder()
                .keywordName(keywordName)
                .build();
        keywordRepository.save(keyword);

        String deviceNum="1234";
        String nickname="dale";
        Integer age=25;
        String sex="m";
        Integer alcoholLevel=3;

        UserInfo userInfo=UserInfo.builder()
                .deviceNum(deviceNum)
                .nickname(nickname)
                .age(age)
                .sex(sex)
                .alcoholLevel(alcoholLevel)
                .build();
        userInfoRepository.save(userInfo);

        UserKeyword userKeyword=new UserKeyword(userInfo, keyword);
    }

}