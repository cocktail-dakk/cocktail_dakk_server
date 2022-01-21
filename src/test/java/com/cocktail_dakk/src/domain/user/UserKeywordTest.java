package com.cocktail_dakk.src.domain.user;

import com.cocktail_dakk.src.domain.cocktail.*;
import com.cocktail_dakk.src.domain.drink.Drink;
import com.cocktail_dakk.src.domain.drink.DrinkRepository;
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

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    CocktailDrinkRepository cocktailDrinkRepository;

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

        // Then
        List<UserInfo> all = userInfoRepository.findAll();
        assertThat(all).isNotEmpty();
        UserInfo byId=all.get(0);
        System.out.println("Find by id success");

        UserInfo userInfo1=byId;
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


        // Then
        List<UserInfo> all = userInfoRepository.findAll();
        assertThat(all).isNotEmpty();
        UserInfo byId=all.get(0);
        System.out.println("Find by id success");

        UserInfo userInfo1=byId;
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

    //기주로 칵테일 조회하는 테스트
    @Test
    @Transactional
    public void testDrinkCocktail(){

        // Given
        CocktailInfo cocktailInfo1=CocktailInfo.builder()
                .englishName("21st Century")
                .koreanName("21세기")
                .description("쓰다")
                .cocktailImageURL("1234")
                .cocktailBackgroundImageURL("5678")
                .recommendImageURL("91011")
                .build();

        CocktailInfo cocktailInfo2=CocktailInfo.builder()
                .englishName("God Father")
                .koreanName("갓 파더")
                .description("모르겠다")
                .cocktailImageURL("abcd")
                .cocktailBackgroundImageURL("efg")
                .recommendImageURL("hijk")
                .build();

        CocktailInfo cocktailInfo3=CocktailInfo.builder()
                .englishName("Gold Rush")
                .koreanName("골드 러시")
                .description("금 맛이다")
                .cocktailImageURL("lmno")
                .cocktailBackgroundImageURL("pqkr")
                .recommendImageURL("stu")
                .build();

        Drink drink1=Drink.builder()
                .drinkName("데킬라")
                .build();

        Drink drink2=Drink.builder()
                .drinkName("위스키")
                .build();

        CocktailDrink cocktailDrink1=new CocktailDrink(cocktailInfo1, drink1);
        CocktailDrink cocktailDrink2=new CocktailDrink(cocktailInfo2, drink2);
        CocktailDrink cocktailDrink3=new CocktailDrink(cocktailInfo3, drink2);

        // when 조인 엔티티 직접 영속화하는 방법
        cocktailInfoRepository.save(cocktailInfo1);
        cocktailInfoRepository.save(cocktailInfo2);
        cocktailInfoRepository.save(cocktailInfo3);

        drinkRepository.save(drink1);
        drinkRepository.save(drink2);

        cocktailDrinkRepository.save(cocktailDrink1);
        cocktailDrinkRepository.save(cocktailDrink2);
        cocktailDrinkRepository.save(cocktailDrink3);

        cocktailInfoRepository.flush();

        // Then
        List<Drink> drinks = drinkRepository.findAll();
        assertThat(drinks.size()).isEqualTo(2);
        System.out.println("find all drink success");

        String[] drinkNameList={"데킬라", "위스키"};
        for(int i=0; i<drinks.size(); i++){
            assertThat(drinks.get(i).getDrinkName()).isEqualTo(drinkNameList[i]);
        }
        System.out.println(" drink name correct");
        
        for(int i=0; i<drinks.size();i ++){
            Drink drink=drinks.get(i);
            List<CocktailDrink> cocktailDrinks = drink.getCocktailDrinks();

            System.out.println(drink.getDrinkName());
            if(drink.getDrinkName()=="데킬라"){
                for(int j=0; j<cocktailDrinks.size(); j++){
                    assertThat(drink1.getCocktailDrinks().get(j).getCocktailInfo().getEnglishName()).isEqualTo(cocktailDrinks.get(j).getCocktailInfo().getEnglishName());
                    System.out.println(cocktailDrinks.get(j).getCocktailInfo().getEnglishName());
                }
            }else if(drink.getDrinkName()=="위스키"){
                for(int j=0; j<cocktailDrinks.size(); j++){
                    assertThat(drink2.getCocktailDrinks().get(j).getCocktailInfo().getEnglishName()).isEqualTo(cocktailDrinks.get(j).getCocktailInfo().getEnglishName());
                    System.out.println(cocktailDrinks.get(j).getCocktailInfo().getEnglishName());
                }
            }
            System.out.println();
        }
        System.out.println("Successfully searched cocktail with drink.");
    }
}