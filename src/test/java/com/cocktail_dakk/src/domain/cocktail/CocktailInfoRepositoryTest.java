package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.drink.Drink;
import com.cocktail_dakk.src.domain.drink.DrinkRepository;
import com.cocktail_dakk.src.domain.keyword.Keyword;
import com.cocktail_dakk.src.domain.keyword.KeywordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class CocktailInfoRepositoryTest {
    @Autowired
    CocktailInfoRepository cocktailInfoRepository;

    @Autowired
    KeywordRepository keywordRepository;

    @Autowired
    CocktailKeywordRepository cocktailKeywordRepository;

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    CocktailDrinkRepository cocktailDrinkRepository;

    @Test
    @Transactional
    public void searchCocktailTest(){
        saveCocktailInfo();

        // Then
        List<CocktailInfo> search = cocktailInfoRepository.findSearch(Pageable.ofSize(10), "깔끔한", "깔끔한", "깔끔한", "깔끔한");
        assertThat(search.size()).isEqualTo(2);
        assertThat(search.get(0).getEnglishName()).isEqualTo("21st Century");
        assertThat(search.get(1).getEnglishName()).isEqualTo("Gold Rush");
        for (CocktailInfo cocktailInfo : search){
            System.out.println("영어 이름: "+cocktailInfo.getEnglishName());
            System.out.println("한국어 이름: "+cocktailInfo.getKoreanName());

            cocktailInfo.getCocktailKeywords().forEach(cocktailKeyword -> System.out.println("키워드: "+cocktailKeyword.getKeyword().getKeywordName()));

            System.out.println("재료: "+cocktailInfo.getIngredient());
        }

    }

    @Test
    @Transactional
    public void searchCocktailFilterTest(){
        saveCocktailInfo();

        // When
        List<String> keywordName=new ArrayList<>();
        keywordName.add("깔끔한");
        keywordName.add("달콤한");

        Integer alcoholLevel=1;
        Integer alcoholLevelRange=7;

        List<String> drinkName=new ArrayList<>();
        drinkName.add("데킬라");
        drinkName.add("위스키");

        // Then
        List<CocktailInfo> searchFilter = cocktailInfoRepository.findSearchFilter(Pageable.ofSize(10), keywordName, alcoholLevel, alcoholLevelRange, drinkName);
        assertThat(searchFilter.get(0).getEnglishName()).isEqualTo("God Father");
        assertThat(searchFilter.get(1).getEnglishName()).isEqualTo("Gold Rush");
        for (CocktailInfo cocktailInfo : searchFilter) {
            System.out.println("이름: "+cocktailInfo.getEnglishName());

            cocktailInfo.getCocktailKeywords().forEach(cocktailKeyword -> System.out.println("키워드: "+cocktailKeyword.getKeyword().getKeywordName()));

            System.out.println("도수: "+cocktailInfo.getAlcoholLevel());

            cocktailInfo.getCocktailDrinks().forEach(cocktailDrink -> System.out.println("기주: "+cocktailDrink.getDrink().getDrinkName()));
        }
    }

    private void saveCocktailInfo(){
        // Given
        CocktailInfo cocktailInfo1=CocktailInfo.builder()
                .englishName("21st Century")
                .koreanName("21세기")
                .description("쓰다")
                .cocktailImageURL("1234")
                .cocktailBackgroundImageURL("5678")
                .recommendImageURL("91011")
                .smallNukkiImageURL("1234123")
                .alcoholLevel(30)
                .ingredient("크림 (15ml),드라이 진 (45ml)")
                .ratingAvg(new BigDecimal("4.0"))
                .status(Status.ACTIVE)
                .build();

        CocktailInfo cocktailInfo2=CocktailInfo.builder()
                .englishName("God Father")
                .koreanName("갓 파더")
                .description("모르겠다")
                .cocktailImageURL("abcd")
                .cocktailBackgroundImageURL("efg")
                .recommendImageURL("hijk")
                .smallNukkiImageURL("1234123")
                .alcoholLevel(1)
                .ingredient("크림 (15ml),드라이 진 (45ml)")
                .ratingAvg(new BigDecimal("4.0"))
                .status(Status.ACTIVE)
                .build();

        CocktailInfo cocktailInfo3=CocktailInfo.builder()
                .englishName("Gold Rush")
                .koreanName("골드 러시")
                .description("금 맛이다")
                .cocktailImageURL("lmno")
                .cocktailBackgroundImageURL("pqkr")
                .recommendImageURL("stu")
                .smallNukkiImageURL("1234123")
                .alcoholLevel(1)
                .ingredient("크림 (15ml),드라이 진 (45ml)")
                .ratingAvg(new BigDecimal("4.0"))
                .status(Status.ACTIVE)
                .build();

        Drink drink1=Drink.builder()
                .drinkName("데킬라")
                .status(Status.ACTIVE)
                .build();

        Drink drink2=Drink.builder()
                .drinkName("위스키")
                .status(Status.ACTIVE)
                .build();

        Keyword keyword1=Keyword.builder()
                .keywordName("깔끔한")
                .build();

        Keyword keyword2=Keyword.builder()
                .keywordName("달콤한")
                .build();

        // 칵테일과 기주 연관관계 설정
        CocktailDrink cocktailDrink1=new CocktailDrink(cocktailInfo1, drink1);
        CocktailDrink cocktailDrink2=new CocktailDrink(cocktailInfo2, drink2);
        CocktailDrink cocktailDrink3=new CocktailDrink(cocktailInfo3, drink2);
        CocktailDrink cocktailDrink4=new CocktailDrink(cocktailInfo2, drink1);

        // 칵테일과 취향 키워드 연관관계 설정
        CocktailKeyword cocktailKeyword1=new CocktailKeyword(cocktailInfo1, keyword1);
        CocktailKeyword cocktailKeyword2=new CocktailKeyword(cocktailInfo2, keyword2);
        CocktailKeyword cocktailKeyword3=new CocktailKeyword(cocktailInfo3, keyword1);

        // when 조인 엔티티 직접 영속화하는 방법
        cocktailInfoRepository.save(cocktailInfo1);
        cocktailInfoRepository.save(cocktailInfo2);
        cocktailInfoRepository.save(cocktailInfo3);

        drinkRepository.save(drink1);
        drinkRepository.save(drink2);

        cocktailDrinkRepository.save(cocktailDrink1);
        cocktailDrinkRepository.save(cocktailDrink2);
        cocktailDrinkRepository.save(cocktailDrink3);
        cocktailDrinkRepository.save(cocktailDrink4);

        keywordRepository.save(keyword1);
        keywordRepository.save(keyword2);

        cocktailKeywordRepository.save(cocktailKeyword1);
        cocktailKeywordRepository.save(cocktailKeyword2);
        cocktailKeywordRepository.save(cocktailKeyword3);

        System.out.println(cocktailInfo1.getEnglishName());
        cocktailInfo1.getCocktailKeywords().forEach(cocktailKeyword -> System.out.println("키워드: "+cocktailKeyword.getKeyword().getKeywordName()));
        System.out.println(cocktailInfo1.getAlcoholLevel());
        cocktailInfo1.getCocktailDrinks().forEach(cocktailDrink -> System.out.println("기주: "+cocktailDrink.getDrink().getDrinkName()));

        System.out.println();

        System.out.println(cocktailInfo2.getEnglishName());
        cocktailInfo2.getCocktailKeywords().forEach(cocktailKeyword -> System.out.println("키워드: "+cocktailKeyword.getKeyword().getKeywordName()));
        System.out.println(cocktailInfo2.getAlcoholLevel());
        cocktailInfo2.getCocktailDrinks().forEach(cocktailDrink -> System.out.println("기주: "+cocktailDrink.getDrink().getDrinkName()));

        System.out.println();

        System.out.println(cocktailInfo3.getEnglishName());
        cocktailInfo3.getCocktailKeywords().forEach(cocktailKeyword -> System.out.println("키워드: "+cocktailKeyword.getKeyword().getKeywordName()));
        System.out.println(cocktailInfo3.getAlcoholLevel());
        cocktailInfo3.getCocktailDrinks().forEach(cocktailDrink -> System.out.println("기주: "+cocktailDrink.getDrink().getDrinkName()));


        cocktailInfoRepository.flush();
    }
}