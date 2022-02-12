package com.cocktail_dakk.src.service;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.BaseResponseStatus;
import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfoRepository;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.domain.user.UserInfoRepository;
import com.cocktail_dakk.src.domain.user.dto.PostRatingRes;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class CocktailServiceTest {

    @Autowired
    private CocktailService cocktailService;
    @Autowired
    private CocktailInfoRepository cocktailInfoRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    @BeforeAll
    private static void beforeAll(@Autowired CocktailInfoRepository cocktailInfoRepository, @Autowired UserInfoRepository userInfoRepository) {
        CocktailInfo cocktailInfo1 = createCocktail("Golden Dream", "골든 드림", "달콤하고 부드러운 맛 덕분에 주로 식후주로 사용되며, 이전 IBA 공식 칵테일에서도 식후주로 분류된 바 있다.",
                "url111", "url222", "url333", 2, Status.ACTIVE);
        CocktailInfo cocktailInfo2 = createCocktail("HAHA", "하하", "두 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 5, Status.INACTIVE);

        cocktailInfoRepository.save(cocktailInfo1);
        cocktailInfoRepository.save(cocktailInfo2);

        UserInfo userInfo1 =createUserInfo("1234","minnie",23,"F",12,Status.ACTIVE);
        UserInfo userInfo2 =createUserInfo("12344","dale",23,"M",2,Status.ACTIVE);
        UserInfo userInfo3 =createUserInfo("12345","jjung",23,"F",12,Status.ACTIVE);

        userInfoRepository.save(userInfo1);
        userInfoRepository.save(userInfo2);
        userInfoRepository.save(userInfo3);
    }

    @Test
//    @DisplayName("별점을 추가한다.")
    @Transactional
    public void addPoint() throws Exception{
        //given
        List<UserInfo> userInfo = userInfoRepository.findAll();
        assertThat(userInfo).isNotEmpty();
        System.out.println("유저 아이디: "+userInfo.get(0).getUserInfoId());
        List<CocktailInfo> cocktailInfo = cocktailInfoRepository.findAll();
        assertThat(cocktailInfo).isNotEmpty();
        System.out.println("칵테일 아이디: "+cocktailInfo.get(0).getCocktailInfoId());

        //when
        PostRatingRes postRatingRes = cocktailService.addPoint(userInfo.get(0), cocktailInfo.get(0), 4.5f);
        //then
        assertThat(postRatingRes.getCocktailId()).isEqualTo(cocktailInfo.get(0).getCocktailInfoId());
    }


    @Test
//    @DisplayName("별점 추가시 이미 등록한 칵테일이라면 예외가 발생한다.")
    @Transactional
    public void addPointException() throws BaseException{
        //when
        List<UserInfo> userInfo = userInfoRepository.findAll();
        assertThat(userInfo).isNotEmpty();
        System.out.println("유저 아이디: "+userInfo.get(0).getUserInfoId());
        List<CocktailInfo> cocktailInfo = cocktailInfoRepository.findAll();
        assertThat(cocktailInfo).isNotEmpty();
        System.out.println("칵테일 아이디: "+cocktailInfo.get(0).getCocktailInfoId());

        cocktailService.addPoint(userInfo.get(0), cocktailInfo.get(0), 4.0f);

        //then
        try {
            cocktailService.addPoint(userInfo.get(0), cocktailInfo.get(0), 3.5f); //한 번 더 작성
        } catch (BaseException e){
            Assertions.assertEquals(BaseResponseStatus.POST_EXISTS_RATING, e.getStatus());
        }
    }

    @Test
//    @DisplayName("존재하지 않는 칵테일일 때 예외처리를 한다.")
    @Transactional
    public void getCocktailInfoException() {
        try {
            cocktailService.getCocktailInfo(3L);
        } catch (BaseException e){
            Assertions.assertEquals(BaseResponseStatus.NOT_EXIST_COCKTAIL, e.getStatus());
        }

        try {
            cocktailService.getCocktailInfo(2L);
        } catch (BaseException e){
            Assertions.assertEquals(BaseResponseStatus.NOT_EXIST_COCKTAIL, e.getStatus());
        }
    }

    @Test
//    @DisplayName("칵테일을 조회한다.")
    @Transactional
    public void getCocktailInfo() throws Exception{
        List<CocktailInfo> all = cocktailInfoRepository.findAll();
        CocktailInfo cocktailInfo1 = all.get(0);
        //when
        CocktailInfo cocktailInfo2 = cocktailService.getCocktailInfo(cocktailInfo1.getCocktailInfoId());
        assertThat(cocktailInfo2).isNotNull();
        System.out.println("칵테일 아이디: "+cocktailInfo2.getCocktailInfoId());
        //then
        assertThat(cocktailInfo2.getCocktailInfoId()).isEqualTo(cocktailInfo1.getCocktailInfoId());
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