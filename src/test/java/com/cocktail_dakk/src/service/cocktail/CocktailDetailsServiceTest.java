package com.cocktail_dakk.src.service.cocktail;

import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfoRepository;
import com.cocktail_dakk.src.domain.cocktail.CocktailToday;
import com.cocktail_dakk.src.domain.cocktail.CocktailTodayRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class CocktailDetailsServiceTest {
    @Autowired
    private CocktailInfoRepository cocktailInfoRepository;


}
