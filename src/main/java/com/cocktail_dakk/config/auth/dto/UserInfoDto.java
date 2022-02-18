package com.cocktail_dakk.config.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserInfoDto {
    private String email;

    @Builder
    public UserInfoDto(String email){
        this.email=email;
    }
}
