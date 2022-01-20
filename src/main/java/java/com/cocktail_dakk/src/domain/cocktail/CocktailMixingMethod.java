package java.com.cocktail_dakk.src.domain.cocktail;

import javax.persistence.*;
import java.com.cocktail_dakk.src.domain.MixingMethod;

@Entity
public class CocktailMixingMethod {

    @Id @GeneratedValue
    private Long cocktailMixingMethodId;

    @ManyToOne(fetch = FetchType.LAZY)
    private MixingMethod mixingMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    private CocktailInfo cocktailInfo;

}
