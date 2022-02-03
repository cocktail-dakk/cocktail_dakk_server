package com.cocktail_dakk.src.domain.cocktail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "CocktailToday")
@Setter
public class CocktailToday {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long cocktailTodayId;

    @ElementCollection
    private List<Long> randomId = new ArrayList<>();
}
