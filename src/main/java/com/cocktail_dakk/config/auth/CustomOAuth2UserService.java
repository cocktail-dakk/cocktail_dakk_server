//package com.cocktail_dakk.config.auth;
//
//import com.cocktail_dakk.config.auth.dto.OAuthAttributes;
//import com.cocktail_dakk.src.domain.user.UserInfo;
//import com.cocktail_dakk.src.domain.user.UserInfoRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@RequiredArgsConstructor
//@Service
//public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//    private final UserInfoRepository userInfoRepository;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate=new DefaultOAuth2UserService();
//        OAuth2User oAuth2User=delegate.loadUser(userRequest);
//
//        String registrationId=userRequest.getClientRegistration().getRegistrationId();
//        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
//
//        OAuthAttributes attributes=OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
//
//        UserInfo userInfo=saveOrUpdate(attributes);
//
//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority(userInfo.getRoleKey())),
//                attributes.getAttributes(),
//                attributes.getNameAttributeKey()
//        );
//    }
//
//    private UserInfo saveOrUpdate(OAuthAttributes attributes){
//        UserInfo userInfo=userInfoRepository.findByEmail(attributes.getEmail()).orElse(attributes.toEntity());
//
//        return userInfoRepository.save(userInfo);
//    }
//}
