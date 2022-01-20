package com.cocktail_dakk.src.domain.user;

import com.cocktail_dakk.src.domain.keyword.Keyword;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cocktail_dakk.src.domain.keyword.KeywordRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserKeywordTest {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    KeywordRepository keywordRepository;

    @Autowired
    UserKeywordRepository userKeywordRepository;

    @Test
    @Transactional(readOnly = true)
    public void createUserKeyword(){
        // Given
        String keywordName="깔끔한";

        Keyword keyword=Keyword.builder()
                .keywordName(keywordName)
                .build();


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


        UserKeyword userKeyword=new UserKeyword(userInfo, keyword);


        // when
        keywordRepository.save(keyword);
        userInfoRepository.save(userInfo);
        userKeywordRepository.save(userKeyword);

        // Then
        Optional<UserInfo> byId = userInfoRepository.findById(2L);
        assertThat(byId).isNotEmpty();
        System.out.println("Find by id success");

        UserInfo userInfo1=byId.orElseThrow(IllegalArgumentException::new);
        assertThat(userInfo.getNickname()).isEqualTo(userInfo1.getNickname());
        System.out.println("UserInfo is same");

        List<UserKeyword> userKeywords = userInfo1.getUserKeywords();
        System.out.println(userKeywords.getClass().getName());

        UserKeyword getUserKeyword=userKeywords.get(0);
        System.out.println(getUserKeyword.getKeyword().getKeywordName());
        assertThat(getUserKeyword.getKeyword().getKeywordName()).isEqualTo(keyword.getKeywordName());
    }
}