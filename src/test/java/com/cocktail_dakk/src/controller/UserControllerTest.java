package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.BaseResponseStatus;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.domain.user.UserInfoRepository;
import com.cocktail_dakk.src.domain.user.dto.UserInfoReq;
import org.junit.jupiter.api.Assertions;
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


    @Test
    @Transactional
    public void deviceNumTest() throws Exception {

        createUser();

        mockMvc.perform(get("/users/device-num")
                        .param("deviceNum", "1234"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.deviceNum").value("1234"));
    }

    private void createUser(){
        UserInfo userInfo = UserInfo.builder()
                .deviceNum("1234")
                .nickname("jjeong")
                .age(22)
                .sex("F")
                .alcoholLevel(0)
                .build();

        userInfoRepository.save(userInfo);
    }

}
