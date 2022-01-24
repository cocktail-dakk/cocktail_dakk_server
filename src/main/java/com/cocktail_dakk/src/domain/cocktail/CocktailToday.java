package com.cocktail_dakk.src.domain.cocktail;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "CocktailToday")
public class CocktailToday {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long cocktailTodayId;

    @ElementCollection
    private List<Long> randomId = new ArrayList<>();
}
