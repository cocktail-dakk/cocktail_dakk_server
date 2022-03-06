package com.cocktail_dakk.src.domain.user.dto;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRatingReq {
    private Long cocktailInfoId;
    private float starPoint;
}
