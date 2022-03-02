package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.mixingMethod.MixingMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "CocktailMixingMethod")
public class CocktailMixingMethod {

    public CocktailMixingMethod(CocktailInfo cocktailInfo, MixingMethod mixingMethod) {
        this.mixingMethod = mixingMethod;
        this.cocktailInfo = cocktailInfo;
        cocktailInfo.getCocktailMixingMethods().add(this);
        mixingMethod.getCocktailMixingMethods().add(this);
    }

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long cocktailMixingMethodId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mixingMethodId")
    private MixingMethod mixingMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cocktailInfoId")
    private CocktailInfo cocktailInfo;

}
