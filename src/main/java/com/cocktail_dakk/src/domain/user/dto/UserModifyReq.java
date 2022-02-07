package com.cocktail_dakk.src.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UserModifyReq {
    private String deviceNum;
    private String nickname;
    private Integer alcoholLevel;
    private String favouritesKeywords;
    private String favouritesDrinks;

}
