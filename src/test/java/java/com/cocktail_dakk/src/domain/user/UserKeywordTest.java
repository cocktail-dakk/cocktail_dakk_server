package java.com.cocktail_dakk.src.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.com.cocktail_dakk.src.domain.Keyword;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserKeywordTest {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Test
    public void createUserKeyword(){
        Keyword keyword=new Keyword();
        UserInfo userInfo=new UserInfo();
    }

}