package com.cocktail_dakk.src.domain.user.dto;

import com.cocktail_dakk.src.domain.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoStatusRes {
    private String email;
    private Boolean doInit;

    @Builder
    public UserInfoStatusRes(String email, Boolean doInit){
        this.email=email;
        this.doInit=doInit;
    }
}
