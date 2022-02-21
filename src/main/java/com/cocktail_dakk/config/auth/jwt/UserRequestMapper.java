//package com.cocktail_dakk.config.auth.jwt;
//
//import com.cocktail_dakk.config.auth.dto.UserInfoDto;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//@Component
//public class UserRequestMapper {
//    public UserInfoDto toDto(OAuth2User oAuth2User){
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//        return UserInfoDto.builder()
//                .email((String) attributes.get("email"))
//                .build();
//    }
//}
