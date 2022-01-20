package java.com.cocktail_dakk.src.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.com.cocktail_dakk.src.domain.keyword.Keyword;
import java.com.cocktail_dakk.src.domain.keyword.KeywordRepository;

@SpringBootTest
class UserKeywordTest {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    KeywordRepository keywordRepository;

    @Test
    public void createUserKeyword(){
        Keyword keyword=new Keyword();
        UserInfo userInfo=new UserInfo();
    }

}