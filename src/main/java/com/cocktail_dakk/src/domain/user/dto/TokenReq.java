package com.cocktail_dakk.src.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenReq {
    private String idToken;

    @Builder
    public TokenReq(String idToken){
        this.idToken=idToken;
    }
}
