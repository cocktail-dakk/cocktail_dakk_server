package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.config.BaseResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class SearchCocktailInfoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Transactional
    public void findTest(){

        //Given
        String url="http://localhost:"+port+"/search/cocktail/?inputStr";

        //when
        ResponseEntity<BaseResponse> responseEntity=testRestTemplate.exchange(url, HttpMethod.GET, null, BaseResponse.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getIsSuccess()).isTrue();
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("요청에 성공하였습니다.");
    }

}