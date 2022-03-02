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
    public void findStatusByEmailTest1(){
        // Given
        // When
        UserInfo userInfo1 = createUser();

        // Then
        Optional<UserInfoStatusProjection> statusByEmail = userInfoRepository.findStatusByEmail(userInfo1.getEmail());
        assertThat(statusByEmail).isNotEmpty();
        UserInfoStatusProjection userInfoStatusProjection = statusByEmail.get();

        Status status = userInfoStatusProjection.getStatus();
        UserInfoStatusRes userInfoStatusRes;
        if(status==null||status==Status.INACTIVE)
            userInfoStatusRes=new UserInfoStatusRes(userInfoStatusProjection.getEmail(), true);
        else
            userInfoStatusRes=new UserInfoStatusRes(userInfoStatusProjection.getEmail(), false);
        assertThat(userInfo1.getEmail()).isEqualTo(userInfoStatusRes.getEmail());
        assertThat(false).isEqualTo(userInfoStatusRes.getDoInit());
    }

    @Test
    @Transactional
    public void findStatusByEmailTest2(){
        // Given
        // When
        UserInfo userInfo1 = createUserWithoutInitInfo();

        // Then
        Optional<UserInfoStatusProjection> statusByEmail = userInfoRepository.findStatusByEmail(userInfo1.getEmail());
        assertThat(statusByEmail).isNotEmpty();
        UserInfoStatusProjection userInfoStatusProjection = statusByEmail.get();

        Status status = userInfoStatusProjection.getStatus();
        UserInfoStatusRes userInfoStatusRes;
        if(status==null||status==Status.INACTIVE)
            userInfoStatusRes=new UserInfoStatusRes(userInfoStatusProjection.getEmail(), true);
        else
            userInfoStatusRes=new UserInfoStatusRes(userInfoStatusProjection.getEmail(), false);
        assertThat(userInfo1.getEmail()).isEqualTo(userInfoStatusRes.getEmail());
        assertThat(true).isEqualTo(userInfoStatusRes.getDoInit());
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

    private UserInfo createUserWithoutInitInfo(){
        UserInfo userInfo = UserInfo.builder()
                .email("test")
                .role(Role.USER)
                .build();

        return userInfoRepository.save(userInfo);
    }
}
