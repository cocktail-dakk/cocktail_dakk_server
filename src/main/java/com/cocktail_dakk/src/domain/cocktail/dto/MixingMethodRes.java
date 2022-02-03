package com.cocktail_dakk.src.domain.cocktail.dto;

import com.cocktail_dakk.src.domain.cocktail.CocktailMixingMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MixingMethodRes {
    private Long mixingMethodId;
    private String mixingMethodName;

    public MixingMethodRes(CocktailMixingMethod cocktailMixingMethod){
        this.mixingMethodId = cocktailMixingMethod.getMixingMethod().getMixingMethodId();
        this.mixingMethodName = cocktailMixingMethod.getMixingMethod().getMixingMethodName();
    }
}
