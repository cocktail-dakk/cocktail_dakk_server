package java.com.cocktail_dakk.src.domain;

import javax.persistence.*;
import java.com.cocktail_dakk.src.domain.cocktail.CocktailMixingMethod;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MixingMethod {

    @Id @GeneratedValue
    private Long mixingMethodId;

    private String mixingMethodName;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany
    private List<CocktailMixingMethod> cocktailMixingMethods=new ArrayList<>();

}
