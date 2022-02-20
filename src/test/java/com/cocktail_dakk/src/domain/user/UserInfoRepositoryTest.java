package com.cocktail_dakk.src.domain.user;

import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.user.dto.UserInfoStatusRes;
import com.cocktail_dakk.src.domain.user.projection.UserInfoStatusProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserInfoRepositoryTest {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Test
    @Transactional
    public void findStatusByEmailTest(){
        // Given
        // When
        UserInfo userInfo = createUser();

        // Then
        Optional<UserInfoStatusProjection> statusByEmail = userInfoRepository.findStatusByEmail(userInfo.getEmail());
        assertThat(statusByEmail).isNotEmpty();
        UserInfoStatusProjection userInfoStatusProjection = statusByEmail.get();

        UserInfoStatusRes userInfoStatusRes=new UserInfoStatusRes(userInfoStatusProjection.getEmail(), userInfoStatusProjection.getStatus());

        assertThat(userInfo.getEmail()).isEqualTo(userInfoStatusRes.getEmail());
        assertThat(userInfo.getStatus()).isEqualTo(userInfoStatusRes.getStatus());
    }

    @Test
    @Transactional
    public void findByEmailTest() {
        // Given
        // When
        UserInfo userInfo = createUser();

        // Then
        Optional<UserInfo> all = userInfoRepository.findByEmail(userInfo.getEmail());
        assertThat(all).isNotEmpty();
        UserInfo byEmail = all.get();
        System.out.println("Find by email success");

        assertThat(userInfo.getEmail()).isEqualTo(byEmail.getEmail());
    }

    private UserInfo createUser(){
        UserInfo userInfo = UserInfo.builder()
                .email("test")
                .role(Role.USER)
                .build();

        userInfo.initUserInfo("jjeong", 22, "F", 0, Status.ACTIVE);

        return userInfoRepository.save(userInfo);
    }
}
