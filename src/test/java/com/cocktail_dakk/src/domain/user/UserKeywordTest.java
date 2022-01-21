package com.cocktail_dakk.src.domain.user;

import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfoRepository;
import com.cocktail_dakk.src.domain.cocktail.CocktailKeyword;
import com.cocktail_dakk.src.domain.cocktail.CocktailKeywordRepository;
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
    CocktailInfoRepository cocktailInfoRepository;

    @Autowired
    UserKeywordRepository userKeywordRepository;

    @Autowired
    CocktailKeywordRepository cocktailKeywordRepository;

    //사용자가 선택한 취향키워드를 데이터베이스에 저장하고 조회할 수 있는지의 테스트
    @Test
    @Transactional
    public void testUserKeyword(){
        // Given
        Keyword keyword=Keyword.builder()
                .keywordName("깔끔한")
                .build();

        UserInfo userInfo=UserInfo.builder()
                .deviceNum("1234")
                .nickname("dale")
                .age(25)
                .sex("m")
                .alcoholLevel(3)
                .build();

        //유저와 취향키워드 간의 연관관계 설정
        UserKeyword userKeyword=new UserKeyword(userInfo, keyword);

        // when 영속성 전이로 조인엔티티를 영속화하는 방법 -> 이 코드 실행할 때 엔티티에 영속성 전이 설정했는지 확인할 것
//        keywordRepository.save(keyword);
//        userInfoRepository.save(userInfo);

        // when 조인에티티를 직접 영속화하는 방법
        keywordRepository.save(keyword);
        userInfoRepository.save(userInfo);
        userKeywordRepository.save(userKeyword);


        userInfoRepository.flush();

        // Then 영속선 전이일 때 Id: 2L 직접 영속화일 땐 Id:2L
        Optional<UserInfo> byId = userInfoRepository.findById(2L);
        assertThat(byId).isNotEmpty();
        System.out.println("Find by id success");

        UserInfo userInfo1=byId.orElseThrow(IllegalArgumentException::new);
        assertThat(userInfo.getDeviceNum()).isEqualTo(userInfo1.getDeviceNum());
        System.out.println("UserInfo is same : "+userInfo1.getNickname());

        List<UserKeyword> userKeywords = userInfo1.getUserKeywords();
        System.out.println(userKeywords.getClass().getName());

        UserKeyword getUserKeyword=userKeywords.get(0);
        System.out.println(getUserKeyword.toString());
        System.out.println(getUserKeyword.getKeyword().getKeywordName());
        assertThat(getUserKeyword.getKeyword().getKeywordName()).isEqualTo(keyword.getKeywordName());
    }

    //사용자가 선택한 취향 키워드에 맞는 칵테일을 조회할 수 있는지의 테스트
    @Test
    @Transactional
    public void testUserKeywordCocktail(){
        // Given
        Keyword keyword1=Keyword.builder()
                .keywordName("깔끔한")
                .build();

        Keyword keyword2=Keyword.builder()
                .keywordName("달콤한")
                .build();
        
        UserInfo userInfo=UserInfo.builder()
                .deviceNum("1234")
                .nickname("dale")
                .age(25)
                .sex("m")
                .alcoholLevel(3)
                .build();
        
        // 유저와 취향 키워드 연관관계 설정
        UserKeyword userKeyword=new UserKeyword(userInfo, keyword1);

        CocktailInfo cocktailInfo1=CocktailInfo.builder()
                .englishName("soju")
                .koreanName("소주")
                .description("쓰다")
                .cocktailImageURL("1234")
                .cocktailBackgroundImageURL("5678")
                .recommendImageURL("91011")
                .build();

        CocktailInfo cocktailInfo2=CocktailInfo.builder()
                .englishName("Makgeolli")
                .koreanName("막걸리")
                .description("달달하다")
                .cocktailImageURL("abcd")
                .cocktailBackgroundImageURL("efgh")
                .recommendImageURL("ijkl")
                .build();

        CocktailInfo cocktailInfo3=CocktailInfo.builder()
                .englishName("vodka")
                .koreanName("보드카")
                .description("엄청 쓰다")
                .cocktailImageURL("oprs")
                .cocktailBackgroundImageURL("tuv")
                .recommendImageURL("dkfjak")
                .build();

        // 칵테일과 취향 키워드 연관관계 설정
        CocktailKeyword cocktailKeyword1=new CocktailKeyword(cocktailInfo1, keyword1);
        CocktailKeyword cocktailKeyword2=new CocktailKeyword(cocktailInfo2, keyword2);
        CocktailKeyword cocktailKeyword3=new CocktailKeyword(cocktailInfo3, keyword1);


        // when 영속성 전이로 조인 엔티티를 영속화하는 방법 -> 이 코드 실행할 때 엔티티에 영속성 전이 설정했는지 확인할 것
//        keywordRepository.save(keyword1);
//        keywordRepository.save(keyword2);
//        userInfoRepository.save(userInfo);
//        cocktailInfoRepository.save(cocktailInfo1);
//        cocktailInfoRepository.save(cocktailInfo2);
//        cocktailInfoRepository.save(cocktailInfo3);
        
        // when 조인 엔티티를 직접 영속화하는 방법
        keywordRepository.save(keyword1);
        keywordRepository.save(keyword2);
        userInfoRepository.save(userInfo);
        userKeywordRepository.save(userKeyword);

        cocktailInfoRepository.save(cocktailInfo1);
        cocktailInfoRepository.save(cocktailInfo2);
        cocktailInfoRepository.save(cocktailInfo3);
        cocktailKeywordRepository.save(cocktailKeyword1);
        cocktailKeywordRepository.save(cocktailKeyword2);
        cocktailKeywordRepository.save(cocktailKeyword3);

        userInfoRepository.flush();


        // Then 영속선 전이일 때 Id: 9L 직접 영속화일 땐 Id:6L
        Optional<UserInfo> byId = userInfoRepository.findById(6L);
        assertThat(byId).isNotEmpty();
        System.out.println("Find by id success");

        UserInfo userInfo1=byId.orElseThrow(IllegalArgumentException::new);
        assertThat(userInfo.getDeviceNum()).isEqualTo(userInfo1.getDeviceNum());
        System.out.println("UserInfo is same : "+userInfo1.getNickname());

        List<UserKeyword> userKeywords = userInfo1.getUserKeywords();
        System.out.println(userKeywords.getClass().getName());

        for(UserKeyword getUserKeyword:userKeywords){
            assertThat(getUserKeyword.getKeyword().getKeywordName()).isEqualTo(keyword1.getKeywordName());
            System.out.println(getUserKeyword.getKeyword().getKeywordName());

            List<CocktailKeyword> cocktailKeywords = getUserKeyword.getKeyword().getCocktailKeywords();
            assertThat(cocktailKeywords.get(0).getCocktailInfo().getEnglishName()).isEqualTo("soju");
            assertThat(cocktailKeywords.get(1).getCocktailInfo().getEnglishName()).isEqualTo("vodka");
            for (CocktailKeyword cocktailKeyword:cocktailKeywords) {
                System.out.println(cocktailKeyword.getCocktailInfo().getEnglishName());
            }
        }
    }

    @Test
    @Transactional
    public void testDrinkCocktail(){

    }
}