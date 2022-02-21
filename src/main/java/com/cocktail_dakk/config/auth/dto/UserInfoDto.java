package com.cocktail_dakk.config.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserInfoDto {
    private String email;
    private String role;

    @Builder
    public UserInfoDto(String email, String role){
        this.email=email;
        this.role=role;
    }

    @Override
    public String toString() {
        return "UserInfoDto{" +
                "email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
