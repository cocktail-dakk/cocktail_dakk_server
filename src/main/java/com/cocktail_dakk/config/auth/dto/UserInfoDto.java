package com.cocktail_dakk.config.auth.dto;

import com.cocktail_dakk.src.domain.user.Role;
import com.cocktail_dakk.src.domain.user.UserInfo;
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

    public UserInfo toEntity(){
        return UserInfo.builder()
                .email(email)
                .role(Role.USER)
                .build();
    }

    @Override
    public String toString() {
        return "UserInfoDto{" +
                "email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
