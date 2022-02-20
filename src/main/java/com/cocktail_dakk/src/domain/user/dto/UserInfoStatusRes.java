package com.cocktail_dakk.src.domain.user.dto;

import com.cocktail_dakk.src.domain.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoStatusRes {
    private String email;
    private Status status;

    @Builder
    public UserInfoStatusRes(String email, Status status){
        this.email=email;
        this.status=status;
    }
}
