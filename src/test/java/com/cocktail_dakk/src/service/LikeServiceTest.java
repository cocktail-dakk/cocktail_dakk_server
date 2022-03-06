package com.cocktail_dakk.src.service;

import com.cocktail_dakk.config.auth.dto.UserInfoDto;
import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfoRepository;
import com.cocktail_dakk.src.domain.user.*;
import com.cocktail_dakk.src.domain.user.dto.UserLikeRes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class LikeServiceTest {

    @Autowired
    private LikeService likeService;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private CocktailInfoRepository cocktailInfoRepository;

    @Autowired
    private UserCocktailRepository userCocktailRepository;

    @Test
    @Transactional
    public void getLikes() throws Exception{
        //given
        createUserAndLikeCocktail();

        //when
        List<UserLikeRes> userLikes = likeService.getUserLikes(userInfoRepository.findAll().get(0));

        //then
        Assertions.assertThat(userLikes.size()).isEqualTo(3);
    }

    @Test
    @Transactional
    public void deleteLike() throws Exception{
        //given
        createUserAndLikeCocktail();

        //when
        UserInfo userInfo = userInfoRepository.findAll().get(0);
        likeService.deleteLike(userInfo,
                userCocktailRepository.findAll().get(0).getCocktailInfo().getCocktailInfoId());

        //then
        Assertions.assertThat(userCocktailRepository.findByUserInfo(userInfo).size()).isEqualTo(2);
    }

    private void createUserAndLikeCocktail(){
        UserInfo userInfo = UserInfo.builder()
                .email("test@test.com")
                .role(Role.USER)
                .build();

        userInfo.initUserInfo("hiroo", 22, "F", 20, Status.ACTIVE);

        UserInfo save = userInfoRepository.save(userInfo);

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .email(save.getEmail())
                .build();

        Authentication authentication = getAuthentication(userInfoDto);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        CocktailInfo cocktailInfo1 = createCocktail("test111", "골든 드림", "달콤하고 부드러운 맛 덕분에 주로 식후주로 사용되며, 이전 IBA 공식 칵테일에서도 식후주로 분류된 바 있다.",
                "url111", "url222", "url333", 2, Status.ACTIVE);
        CocktailInfo cocktailInfo2 = createCocktail("test222", "우우", "두 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 14, Status.ACTIVE);
        CocktailInfo cocktailInfo3 = createCocktail("test333", "트리니다드 사워", "두 번째 테스트용 칵테일 데이터",
                "url222", "url2222", "url22222", 10, Status.ACTIVE);

        cocktailInfoRepository.save(cocktailInfo1);
        cocktailInfoRepository.save(cocktailInfo2);
        cocktailInfoRepository.save(cocktailInfo3);

        saveUserCocktail(userInfo, cocktailInfo1);
        saveUserCocktail(userInfo, cocktailInfo2);
        saveUserCocktail(userInfo, cocktailInfo3);

    }

    private void saveUserCocktail(UserInfo userInfo, CocktailInfo cocktailInfo) {
        userCocktailRepository.save(UserCocktail.builder()
                .cocktailInfo(cocktailInfo)
                .userInfo(userInfo)
                .build());
    }

    private CocktailInfo createCocktail(String englishName, String koreanName, String description, String url1,
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

    private Authentication getAuthentication(UserInfoDto member){
        return new UsernamePasswordAuthenticationToken(member, "", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

}