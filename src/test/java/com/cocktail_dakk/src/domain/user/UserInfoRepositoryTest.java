package com.cocktail_dakk.src.domain.user;

import com.cocktail_dakk.src.domain.Status;
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
    public void findByDeviceNumTest() {
        // Given
        UserInfo userInfo = UserInfo.builder()
                .deviceNum("1234")
                .nickname("jjeong")
                .age(22)
                .sex("F")
                .alcoholLevel(18)
                .status(Status.ACTIVE)
                .build();

        // When
        userInfoRepository.save(userInfo);
        userInfoRepository.flush();

        // Then
        Optional<UserInfo> all = userInfoRepository.findByDeviceNum(userInfo.getDeviceNum());
        assertThat(all).isNotEmpty();
        UserInfo byDeviceNum = all.get();
        System.out.println("Find by deviceNum success");

        assertThat(userInfo.getDeviceNum()).isEqualTo(all.get().getDeviceNum());

    }
}
