package com.cocktail_dakk.src.controller;


import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.BaseResponse;
import com.cocktail_dakk.src.domain.cocktail.dto.CocktailDetailsInfoRes;
import com.cocktail_dakk.src.service.CocktailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cocktails")
public class CocktailDetailInfoController {
    @Autowired
    private final CocktailService cocktailService;

    @GetMapping("/details")
    public BaseResponse<CocktailDetailsInfoRes> getCocktailInfo(@RequestParam Long id){
//        return cocktailService.getCocktailDetailsInfo(id);
        try {
            return new BaseResponse<>(cocktailService.getCocktailDetailsInfo(id));
        } catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

}
