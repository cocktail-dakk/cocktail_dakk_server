package com.cocktail_dakk.src.domain.user;

import com.cocktail_dakk.src.domain.cocktail.*;
import com.cocktail_dakk.src.domain.drink.Drink;
import com.cocktail_dakk.src.domain.drink.DrinkRepository;
import com.cocktail_dakk.src.domain.ingredient.Ingredient;
import com.cocktail_dakk.src.domain.ingredient.IngredientRepository;
import com.cocktail_dakk.src.domain.keyword.Keyword;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cocktail_dakk.src.domain.keyword.KeywordRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    CocktailIngredientRepository cocktailIngredientRepository;

    @Autowired
    UserCocktailRepository userCocktailRepository;

    @Autowired
    UserDrinkRepository userDrinkRepository;

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

        assertThat(userInfo.getDeviceNum()).isEqualTo(byId.getDeviceNum());
        System.out.println("UserInfo is same : "+ byId.getNickname());

        List<UserKeyword> userKeywords = byId.getUserKeywords();
        assertThat(userKeywords.contains(userKeyword)).isTrue();

        UserKeyword getUserKeyword=userKeywords.get(0);
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

        assertThat(userInfo.getDeviceNum()).isEqualTo(byId.getDeviceNum());
        System.out.println("UserInfo is same : "+ byId.getNickname());

        List<UserKeyword> userKeywords = byId.getUserKeywords();
        assertThat(userKeywords.contains(userKeyword)).isTrue();

        for(UserKeyword getUserKeyword:userKeywords){
            System.out.println(getUserKeyword.getKeyword().getKeywordName());

            List<CocktailKeyword> cocktailKeywords = getUserKeyword.getKeyword().getCocktailKeywords();
            assertThat(cocktailKeywords.contains(cocktailKeyword1)).isTrue();
            assertThat(cocktailKeywords.contains(cocktailKeyword3)).isTrue();
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

        // 칵테일과 기주 연관관계 설정
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

        assertThat(drinks.contains(drink1)).isTrue();
        assertThat(drinks.contains(drink2)).isTrue();
        System.out.println(" drink name correct");

        for (Drink drink : drinks) {
            List<CocktailDrink> cocktailDrinks = drink.getCocktailDrinks();

            System.out.println(drink.getDrinkName());
            if (drink.getDrinkName().equals("데킬라")) {
                assertThat(cocktailDrinks.contains(cocktailDrink1)).isTrue();
            } else if (drink.getDrinkName().equals("위스키")) {
                assertThat(cocktailDrinks.contains(cocktailDrink2)).isTrue();
                assertThat(cocktailDrinks.contains(cocktailDrink3)).isTrue();
            }
            for (CocktailDrink cocktailDrink : cocktailDrinks) {
                System.out.println(cocktailDrink.getCocktailInfo().getEnglishName());
            }
            System.out.println();
        }
        System.out.println("Successfully searched cocktail with drink.");
    }

    //재료로 칵테일 조회하는 테스트
    @Test
    @Transactional
    public void testIngredientCocktail(){

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

        Ingredient ingredient1= Ingredient.builder()
                .ingredientName("레몬즙")
                .build();
        Ingredient ingredient2= Ingredient.builder()
                .ingredientName("아마레또")
                .build();

        CocktailIngredient cocktailIngredient1=new CocktailIngredient(cocktailInfo1, ingredient1, "ml", 23.0);
        CocktailIngredient cocktailIngredient2=new CocktailIngredient(cocktailInfo2, ingredient2, "ml", 10.0);
        CocktailIngredient cocktailIngredient3=new CocktailIngredient(cocktailInfo3, ingredient1, "ml", 22.5);

        // when 조인 엔티티 직접 영속화하는 방법
        cocktailInfoRepository.save(cocktailInfo1);
        cocktailInfoRepository.save(cocktailInfo2);
        cocktailInfoRepository.save(cocktailInfo3);

        ingredientRepository.save(ingredient1);
        ingredientRepository.save(ingredient2);

        cocktailIngredientRepository.save(cocktailIngredient1);
        cocktailIngredientRepository.save(cocktailIngredient2);
        cocktailIngredientRepository.save(cocktailIngredient3);

        cocktailInfoRepository.flush();

        // Then
        List<Ingredient> ingredients = ingredientRepository.findAll();
        assertThat(ingredients.size()).isEqualTo(2);
        System.out.println("find all ingredient success");

        assertThat(ingredients.contains(ingredient1)).isTrue();
        assertThat(ingredients.contains(ingredient2)).isTrue();
        System.out.println("ingredient name correct");

        for (Ingredient ingredient : ingredients) {
            List<CocktailIngredient> cocktailIngredients = ingredient.getCocktailIngredients();

            System.out.println(ingredient.getIngredientName());
            if (ingredient.getIngredientName().equals("레몬즙")) {
                assertThat(cocktailIngredients.contains(cocktailIngredient1)).isTrue();
                assertThat(cocktailIngredients.contains(cocktailIngredient3)).isTrue();
            } else if (ingredient.getIngredientName().equals("아마레또")) {
                assertThat(cocktailIngredients.contains(cocktailIngredient2)).isTrue();
            }
            for (CocktailIngredient cocktailIngredient : cocktailIngredients) {
                System.out.println(cocktailIngredient.getCocktailInfo().getEnglishName());
            }
            System.out.println();
        }
        System.out.println("Successfully searched cocktail with ingredient.");
    }

    //사용자가 즐겨찾기한 칵테일 조회
    @Test
    @Transactional
    public void testUserCocktail(){

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

        UserInfo userInfo1=UserInfo.builder()
                .deviceNum("1234")
                .nickname("dale")
                .age(25)
                .sex("m")
                .alcoholLevel(3)
                .build();

        UserInfo userInfo2=UserInfo.builder()
                .deviceNum("5678")
                .nickname("jimin")
                .age(23)
                .sex("w")
                .alcoholLevel(3)
                .build();

        UserCocktail userCocktail1=new UserCocktail(userInfo1, cocktailInfo1, 4.5, "good");
        UserCocktail userCocktail2=new UserCocktail(userInfo1, cocktailInfo2, 5.0, "great");
        UserCocktail userCocktail3=new UserCocktail(userInfo2, cocktailInfo3, 4.5, "yeah~");

        // when 조인 엔티티 직접 영속화하는 방법
        cocktailInfoRepository.save(cocktailInfo1);
        cocktailInfoRepository.save(cocktailInfo2);
        cocktailInfoRepository.save(cocktailInfo3);

        userInfoRepository.save(userInfo1);
        userInfoRepository.save(userInfo2);

        userCocktailRepository.save(userCocktail1);
        userCocktailRepository.save(userCocktail2);
        userCocktailRepository.save(userCocktail3);

        userInfoRepository.flush();

        // Then
        List<UserInfo> all = userInfoRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        System.out.println("Find All success");

        for (UserInfo userInfo : all) {
            List<UserCocktail> userCocktails = userInfo.getUserCocktails();

            System.out.println(userInfo.getNickname());
            if (userInfo.getDeviceNum().equals("1234")) {
                assertThat(userCocktails.contains(userCocktail1)).isTrue();
                assertThat(userCocktails.contains(userCocktail2)).isTrue();
            } else if (userInfo.getDeviceNum().equals("5678")) {
                assertThat(userCocktails.contains(userCocktail3)).isTrue();
            }
            for (UserCocktail userCocktail : userCocktails) {
                System.out.println(userCocktail.getCocktailInfo().getEnglishName());
            }
            System.out.println();
        }
    }


    //사용자가 선택한 선호기주를 데이터베이스에 저장하고 조회할 수 있는지의 테스트
    @Test
    @Transactional
    public void testUserDrink(){
        // Given
        Drink drink=Drink.builder()
                .drinkName("보드카")
                .build();

        UserInfo userInfo=UserInfo.builder()
                .deviceNum("1234")
                .nickname("dale")
                .age(25)
                .sex("m")
                .alcoholLevel(3)
                .build();

        //유저와 선호 기주 간의 연관관계 설정
        UserDrink userDrink=new UserDrink(userInfo, drink);

        // when 조인에티티를 직접 영속화하는 방법
        drinkRepository.save(drink);
        userInfoRepository.save(userInfo);
        userDrinkRepository.save(userDrink);

        userInfoRepository.flush();

        // Then
        List<UserInfo> all = userInfoRepository.findAll();
        assertThat(all).isNotEmpty();
        UserInfo byId=all.get(0);
        System.out.println("Find by id success");

        assertThat(userInfo.getDeviceNum()).isEqualTo(byId.getDeviceNum());
        System.out.println("UserInfo is same : "+ byId.getNickname());

        List<UserDrink> userDrinks = byId.getUserDrinks();

        assertThat(userDrinks.contains(userDrink)).isTrue();
        System.out.println(userDrink.getDrink().getDrinkName());
    }

    //사용자가 선택한 선호 기주에 맞는 칵테일을 조회할 수 있는지의 테스트
    @Test
    @Transactional
    public void testUserDrinkCocktail(){
        // Given
        Drink drink1=Drink.builder()
                .drinkName("데킬라")
                .build();

        Drink drink2=Drink.builder()
                .drinkName("위스키")
                .build();

        UserInfo userInfo=UserInfo.builder()
                .deviceNum("1234")
                .nickname("dale")
                .age(25)
                .sex("m")
                .alcoholLevel(3)
                .build();

        // 유저와 선호 기주 연관관계 설정
        UserDrink userDrink=new UserDrink(userInfo, drink2);

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

        // 칵테일과 선호 기주 연관관계 설정
        CocktailDrink cocktailDrink1=new CocktailDrink(cocktailInfo1, drink1);
        CocktailDrink cocktailDrink2=new CocktailDrink(cocktailInfo2, drink2);
        CocktailDrink cocktailDrink3=new CocktailDrink(cocktailInfo3, drink2);

        // when 조인 엔티티를 직접 영속화하는 방법
        drinkRepository.save(drink1);
        drinkRepository.save(drink2);
        userInfoRepository.save(userInfo);
        userDrinkRepository.save(userDrink);

        cocktailInfoRepository.save(cocktailInfo1);
        cocktailInfoRepository.save(cocktailInfo2);
        cocktailInfoRepository.save(cocktailInfo3);
        cocktailDrinkRepository.save(cocktailDrink1);
        cocktailDrinkRepository.save(cocktailDrink2);
        cocktailDrinkRepository.save(cocktailDrink3);

        userInfoRepository.flush();


        // Then
        List<UserInfo> all = userInfoRepository.findAll();
        assertThat(all).isNotEmpty();
        UserInfo byId=all.get(0);
        System.out.println("Find by id success");

        assertThat(userInfo.getDeviceNum()).isEqualTo(byId.getDeviceNum());
        System.out.println("UserInfo is same : "+ byId.getNickname());

        List<UserDrink> userDrinks = byId.getUserDrinks();

        for(UserDrink getUserDrink:userDrinks){
            assertThat(getUserDrink.getDrink().getDrinkName()).isEqualTo(drink2.getDrinkName());
            System.out.println(getUserDrink.getDrink().getDrinkName());

            List<CocktailDrink> cocktailDrinks = getUserDrink.getDrink().getCocktailDrinks();
            assertThat(cocktailDrinks.contains(cocktailDrink2)).isTrue();
            assertThat(cocktailDrinks.contains(cocktailDrink3)).isTrue();
            for (CocktailDrink cocktailDrink:cocktailDrinks) {
                System.out.println(cocktailDrink.getCocktailInfo().getEnglishName());
            }
        }
    }
}