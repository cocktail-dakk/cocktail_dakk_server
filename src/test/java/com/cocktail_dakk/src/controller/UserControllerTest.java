package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.config.auth.jwt.Token;
import com.cocktail_dakk.config.auth.jwt.TokenService;
import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.user.Role;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.domain.user.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    TokenService tokenService;

    @Test
    @Transactional
    public void userInfoTest() throws Exception {

        Token user = createUser();

        mockMvc.perform(get("/users/info")
                        .header("Auth", user.getToken()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.email").value("test"));
    }

    private Token createUser(){
        UserInfo userInfo = UserInfo.builder()
                .email("test")
                .role(Role.USER)
                .build();

        userInfo.initUserInfo("jjeong", 22, "F", 0, Status.ACTIVE);

        UserInfo save = userInfoRepository.save(userInfo);

        return tokenService.generateToken(save.getEmail(), save.getRoleKey());
    }

}
