//package com.cocktail_dakk.config.auth.dto;
//
//import com.cocktail_dakk.src.domain.user.Role;
//import com.cocktail_dakk.src.domain.user.UserInfo;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.util.Map;
//
//@Getter
//public class OAuthAttributes {
//    private final Map<String, Object> attributes;
//    private final String nameAttributeKey;
//    private final String email;
//
//    @Builder
//    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String email){
//        this.attributes=attributes;
//        this.nameAttributeKey=nameAttributeKey;
//        this.email=email;
//    }
//
//    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String , Object> attributes){
//        return ofGoogle(userNameAttributeName, attributes);
//    }
//
//    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
//        return OAuthAttributes.builder()
//                .email((String) attributes.get("email"))
//                .attributes(attributes)
//                .nameAttributeKey(userNameAttributeName)
//                .build();
//    }
//
//    public UserInfo toEntity(){
//        return UserInfo.builder()
//                .email(email)
//                .role(Role.USER)
//                .build();
//    }
//}
