package com.cocktail_dakk.src.domain.mixingMethod;

import com.cocktail_dakk.src.domain.Status;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.cocktail_dakk.src.domain.cocktail.CocktailMixingMethod;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "MixingMethod")
public class MixingMethod {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long mixingMethodId;

    @Column(name = "mixingMethodName")
    @NotNull
    private String mixingMethodName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @OneToMany
    private List<CocktailMixingMethod> cocktailMixingMethods=new ArrayList<>();

    @Builder
    public MixingMethod(String mixingMethodName) {
        this.mixingMethodName = mixingMethodName;
    }

}
