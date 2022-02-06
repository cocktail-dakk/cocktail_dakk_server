package com.cocktail_dakk.src.service;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.src.domain.cocktail.*;
import com.cocktail_dakk.src.domain.cocktail.dto.*;
//import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
//import com.cocktail_dakk.src.domain.cocktail.CocktailInfoRepository;
//import com.cocktail_dakk.src.domain.cocktail.CocktailToday;
//import com.cocktail_dakk.src.domain.cocktail.CocktailTodayRepository;
//import com.cocktail_dakk.src.domain.cocktail.dto.GetTodayCocktailInfoRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.cocktail_dakk.config.BaseResponseStatus.RESPONSE_ERROR;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CocktailService {
    private final CocktailInfoRepository cocktailInfoRepository;

    //칵테일 상세페이지 띄우기
    public CocktailDetailsInfoRes getCocktailDetailsInfo(Long id) throws BaseException {
        try {
            CocktailInfo detailCocktail = cocktailInfoRepository.findByCocktailInfoId(id);
            return new CocktailDetailsInfoRes(detailCocktail);
        } catch (Exception e){
            throw new BaseException(RESPONSE_ERROR);
        }
    }
}