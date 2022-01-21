package com.cocktail_dakk.src.domain.mixingMethod;

import com.cocktail_dakk.src.domain.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.cocktail_dakk.src.domain.cocktail.CocktailMixingMethod;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class MixingMethod {

    @Id @GeneratedValue
    private Long mixingMethodId;

    private String mixingMethodName;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany
    private List<CocktailMixingMethod> cocktailMixingMethods=new ArrayList<>();

}