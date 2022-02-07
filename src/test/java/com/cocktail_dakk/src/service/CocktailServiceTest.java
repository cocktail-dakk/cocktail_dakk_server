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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

    @BeforeEach
    public void beforeEach() {
        CocktailInfo cocktailInfo1 = createCocktail(1L,"Golden Dream", "골든 드림", "달콤하고 부드러운 맛 덕분에 주로 식후주로 사용되며, 이전 IBA 공식 칵테일에서도 식후주로 분류된 바 있다.",
                "url111", "url222", "url333", 2, Status.ACTIVE);
        CocktailInfo cocktailInfo2 = createCocktail(2L,"HAHA", "하하", "두 번째 테스트용 칵테일 데이터",
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
    @DisplayName("별점을 추가한다.")
    public void addPoint() throws Exception{
        //given
        Optional<UserInfo> userInfo = userInfoRepository.findById(1L);
        Optional<CocktailInfo> cocktailInfo = cocktailInfoRepository.findById(1L);

        //when
        PostRatingRes postRatingRes = cocktailService.addPoint(userInfo.get(), cocktailInfo.get(), 4.5f);
        //then
        assertThat(postRatingRes.getCocktailId()).isEqualTo(cocktailInfo.get().getCocktailInfoId());
    }


    @Test
    @DisplayName("별점 추가시 이미 등록한 칵테일이라면 예외가 발생한다.")
    public void addPointException() throws BaseException{
        //when
        Optional<UserInfo> userInfo = userInfoRepository.findById(2L);
        Optional<CocktailInfo> cocktailInfo = cocktailInfoRepository.findById(1L);

        cocktailService.addPoint(userInfo.get(), cocktailInfo.get(), 4.0f);

        //then
        try {
            cocktailService.addPoint(userInfo.get(), cocktailInfo.get(), 3.5f); //한 번 더 작성
        } catch (BaseException e){
            Assertions.assertEquals(BaseResponseStatus.POST_EXISTS_RATING, e.getStatus());
        }
    }

    @Test
    @DisplayName("존재하지 않는 칵테일일 때 예외처리를 한다.")
    public void getCocktailInfoException() throws BaseException{
        try {
            cocktailService.getCocktailInfo(3L);
        } catch (BaseException e){
            Assertions.assertEquals(BaseResponseStatus.NOT_EXIST_USER, e.getStatus());
        }

        try {
            cocktailService.getCocktailInfo(2L);
        } catch (BaseException e){
            Assertions.assertEquals(BaseResponseStatus.NOT_EXIST_USER, e.getStatus());
        }
    }
    
    @Test
    @DisplayName("칵테일을 조회한다.")
    public void getCocktailInfo() throws Exception{
        //when
        CocktailInfo cocktailInfo = cocktailService.getCocktailInfo(1L);
        //then
        assertThat(cocktailInfo.getCocktailInfoId()).isEqualTo(1L);
    }

    private CocktailInfo createCocktail(Long id, String englishName, String koreanName, String description, String url1,
                                        String url2, String url3, int level, Status status) {

        return CocktailInfo.builder()
                .cocktailInfoId(id)
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

    private UserInfo createUserInfo(String deviceNum, String nickname, Integer age, String sex, Integer alcoholLevel, Status status) {
        UserInfo userInfo=UserInfo.builder()
                .deviceNum(deviceNum)
                .nickname(nickname)
                .age(age)
                .sex(sex)
                .alcoholLevel(alcoholLevel)
                .status(status)
                .build();

        userInfoRepository.save(userInfo);
        return userInfo;
    }
}