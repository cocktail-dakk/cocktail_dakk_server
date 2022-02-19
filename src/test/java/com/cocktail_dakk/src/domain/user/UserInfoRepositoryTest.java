package com.cocktail_dakk.src.domain.user;

import com.cocktail_dakk.config.auth.jwt.Token;
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
