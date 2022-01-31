package com.cocktail_dakk.src.controller;


import com.cocktail_dakk.src.domain.cocktail.dto.CocktailDetailsInfoRes;
import com.cocktail_dakk.src.service.CocktailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cocktails")
public class CocktailDetailInfoController {
    @Autowired
    private final CocktailService cocktailService;

    public CocktailDetailsInfoRes getCocktailInfo(Long id){
        return cocktailService.getCocktailDetailsInfo(id);
    }

}
