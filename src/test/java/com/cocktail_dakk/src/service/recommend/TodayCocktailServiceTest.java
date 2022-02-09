package com.cocktail_dakk.src.service.recommend;

import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfoRepository;
import com.cocktail_dakk.src.domain.cocktail.CocktailToday;
import com.cocktail_dakk.src.domain.cocktail.CocktailTodayRepository;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.domain.user.UserInfoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class TodayCocktailServiceTest {
    @Autowired
    private TodayCocktailService todayCocktailService;
    @Autowired
    private CocktailTodayRepository cocktailTodayRepository;
    @Autowired
    private CocktailInfoRepository cocktailInfoRepository;

    @BeforeAll
    private static void beforeAll(@Autowired CocktailInfoRepository cocktailInfoRepository, @Autowired UserInfoRepository userInfoRepository) {
        CocktailInfo cocktailInfo1 = createCocktail("Golden Dream", "골든 드림", "달콤하고 부드러운 맛 덕분에 주로 식후주로 사용되며, 이전 IBA 공식 칵테일에서도 식후주로 분류된 바 있다.",
                "url111", "url222", "url333", 2, Status.INACTIVE);
        CocktailInfo cocktailInfo2 = createCocktail("cocktailInfo2", "칵테일정보2", "두 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 5, Status.INACTIVE);
        CocktailInfo cocktailInfo3 = createCocktail("cocktailInfo3", "칵테일정보3", "세 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 7, Status.INACTIVE);
        CocktailInfo cocktailInfo4 = createCocktail("cocktailInfo4", "칵테일정보4", "네 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 10, Status.INACTIVE);
        CocktailInfo cocktailInfo5 = createCocktail("cocktailInfo5", "칵테일정보5", "다섯 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 4, Status.ACTIVE);
        CocktailInfo cocktailInfo6 = createCocktail("cocktailInfo6", "칵테일정보6", "여섯 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 2, Status.ACTIVE);
        CocktailInfo cocktailInfo7 = createCocktail("cocktailInfo7", "칵테일정보7", "여섯 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 2, Status.ACTIVE);
        CocktailInfo cocktailInfo8 = createCocktail("cocktailInfo8", "칵테일정보8", "여섯 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 2, Status.ACTIVE);
        CocktailInfo cocktailInfo9 = createCocktail("cocktailInfo9", "칵테일정보9", "여섯 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 2, Status.ACTIVE);
        CocktailInfo cocktailInfo10 = createCocktail("cocktailInfo10", "칵테일정보10", "여섯 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 2, Status.ACTIVE);

        cocktailInfoRepository.save(cocktailInfo1);
        cocktailInfoRepository.save(cocktailInfo2);
        cocktailInfoRepository.save(cocktailInfo3);
        cocktailInfoRepository.save(cocktailInfo4);
        cocktailInfoRepository.save(cocktailInfo5);
        cocktailInfoRepository.save(cocktailInfo6);
        cocktailInfoRepository.save(cocktailInfo7);
        cocktailInfoRepository.save(cocktailInfo8);
        cocktailInfoRepository.save(cocktailInfo9);
        cocktailInfoRepository.save(cocktailInfo10);

        UserInfo userInfo1 =createUserInfo("1234","minnie",23,"F",12,Status.ACTIVE);
        UserInfo userInfo2 =createUserInfo("12344","dale",23,"M",2,Status.ACTIVE);
        UserInfo userInfo3 =createUserInfo("12345","jjung",23,"F",12,Status.ACTIVE);

        userInfoRepository.save(userInfo1);
        userInfoRepository.save(userInfo2);
        userInfoRepository.save(userInfo3);
    }


    @Test
    @Transactional
    @DisplayName("랜덤 칵테일을 추출한다.")
    public void cocktailToday() throws Exception{

        //when
        todayCocktailService.getRandomCocktailId();

        //then
        List<CocktailToday> todayCocktails = cocktailTodayRepository.findAll();

        System.out.println("===========test===========");
        for (CocktailToday cocktailToday : todayCocktails) {
            System.out.println(cocktailToday.getRandomId());
        }
        assertThat(todayCocktails.get(0).getRandomId().size()).isEqualTo(5);

    }


    @Test
    @DisplayName("active 상태인 칵테일을 추출한다.")
    public void getActiveCocktail() throws Exception{
        //when
        List<CocktailInfo> allByStatus = cocktailInfoRepository.findAllByStatus(Status.ACTIVE);
        //then
        assertThat(allByStatus.size()).isEqualTo(6);
    }

    private static CocktailInfo createCocktail(String englishName, String koreanName, String description, String url1,
                                               String url2, String url3, int level, Status status) {

        return CocktailInfo.builder()
                .englishName(englishName)
                .koreanName(koreanName)
                .description(description)
                .cocktailImageURL(url1)
                .cocktailBackgroundImageURL(url2)
                .recommendImageURL(url3)
                .alcoholLevel(level)
                .status(status)
                .build();
    }

    private static UserInfo createUserInfo(String deviceNum, String nickname, Integer age, String sex, Integer alcoholLevel, Status status) {

        return UserInfo.builder()
                .deviceNum(deviceNum)
                .nickname(nickname)
                .age(age)
                .sex(sex)
                .alcoholLevel(alcoholLevel)
                .status(status)
                .build();
    }

}