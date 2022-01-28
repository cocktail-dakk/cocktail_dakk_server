package com.cocktail_dakk.src.service;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.BaseResponseStatus;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfoRepository;
import com.cocktail_dakk.src.domain.cocktail.dto.SearchCocktailInfoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.cocktail_dakk.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@Transactional(readOnly = true)
public class SearchCocktailInfoService{

    @Autowired
    CocktailInfoRepository cocktailInfoRepository;

    public Page<SearchCocktailInfoRes> findByInputStrAll(Pageable pageable, String inputStr) throws BaseException{
        try {
            List<SearchCocktailInfoRes> cocktailInfoResList = cocktailInfoRepository.findAllByKoreanNameContainingOrEnglishNameContainingOrIngredientContaining(pageable, inputStr, inputStr, inputStr)
                    .stream().map(SearchCocktailInfoRes::new)
                    .collect(Collectors.toList());

            return new PageImpl<>(cocktailInfoResList);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public Page<SearchCocktailInfoRes> findAll(Pageable pageable) throws BaseException{
        try {
            List<SearchCocktailInfoRes> cocktailInfoResList = cocktailInfoRepository.findAll(pageable)
                    .stream().map(SearchCocktailInfoRes::new)
                    .collect(Collectors.toList());

            return new PageImpl<>(cocktailInfoResList);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}