package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.BaseResponse;
import com.cocktail_dakk.config.auth.dto.UserInfoDto;
import com.cocktail_dakk.config.auth.jwt.Token;
import com.cocktail_dakk.config.auth.jwt.TokenService;
import com.cocktail_dakk.src.domain.user.Role;
import com.cocktail_dakk.src.domain.user.dto.*;
import com.cocktail_dakk.src.service.UserInfoService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import static com.cocktail_dakk.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserInfoService userService;

    @Autowired
    TokenService tokenService;

    @Value("${client.id:not found!}")
    String clientId;


    @ResponseBody
    @GetMapping("/status")
    public BaseResponse<UserInfoStatusRes> getUserInfoStatus(){
        try {
            return new BaseResponse<>(userService.getUserInfoStatus());
        } catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/info")
    public BaseResponse<UserInfoRes> getUserInfoId(){
        try {
            return new BaseResponse<>(new UserInfoRes(userService.getUserInfo()));
        } catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @PostMapping("/info")
    public BaseResponse<UserInfoRes> initUserInfoId(@RequestBody UserInfoReq userInfoReq){
        try {
            return new BaseResponse<>(userService.initUser(userInfoReq));
        } catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PatchMapping("/modify")
    public BaseResponse<UserInfoRes> modifyUser(@RequestBody UserModifyReq userModifyReq){
        try {
            return new BaseResponse<>(userService.modifyUser(userModifyReq));
        }catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PostMapping("/tokensignin")
    public BaseResponse<Token> token(@RequestBody TokenReq tokenReq){
            HttpTransport transport=new NetHttpTransport();
            JacksonFactory factory = JacksonFactory.getDefaultInstance();

            System.out.println(clientId);
            GoogleIdTokenVerifier verifier=new GoogleIdTokenVerifier.Builder(transport, factory)
                    .setAudience(Collections.singletonList(clientId))
                    .build();
            String userId=null;
            String email=null;

            GoogleIdToken idToken=null;
            try {
                 idToken = verifier.verify(tokenReq.getIdToken());
            } catch (GeneralSecurityException | IOException e) {
                return new BaseResponse<>(ID_TOKEN_VERIFY_ERROR);
            }

        if(idToken!=null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                userId = payload.getSubject();
                email = payload.getEmail();
                Object audience = payload.getAudience();
                String issuer = payload.getIssuer();
                Long initUnixTime=System.currentTimeMillis()/1000L;
                Long expirationTimeSeconds = payload.getExpirationTimeSeconds();
                System.out.println("USER ID: " + userId);
                System.out.println("EMAIL: " + email);
                System.out.println("AUD: "+audience.toString());
                System.out.println("ISS: "+issuer);
                System.out.println("CUR: "+initUnixTime);
                System.out.println("EXP: "+expirationTimeSeconds);

                if(!audience.toString().equals(clientId)){
                    return new BaseResponse<>(ID_TOKEN_NOT_VALID_ERROR);
                }
                if(!issuer.equals("https://accounts.google.com")&&!issuer.equals("accounts.google.com")){
                    return new BaseResponse<>(ID_TOKEN_NOT_VALID_ERROR);
                }
                if(expirationTimeSeconds<(System.currentTimeMillis()/1000L)){
                    return new BaseResponse<>(ID_TOKEN_EXPIRED_ERROR);
                }
                UserInfoDto userInfoDto=new UserInfoDto(email,Role.USER.getKey());
                try {
                    userService.saveOrUpdate(userInfoDto);
                }catch(BaseException e){
                    return new BaseResponse<>(e.getStatus());
                }

                Token token = tokenService.generateToken(email, Role.USER.getKey());
                return new BaseResponse<>(token);
            }
            return new BaseResponse<>(ID_TOKEN_EMPTY_ERROR);
//            RestTemplate restTemplate=new RestTemplate();
//
//            HttpHeaders headers=new HttpHeaders();
//
//            UriComponentsBuilder builder=UriComponentsBuilder.fromHttpUrl("https://oauth2.googleapis.com/tokeninfo")
//                    .queryParam("id_token", tokenReq.getIdToken());
//
//            HttpEntity<?> entity = new HttpEntity<>(headers);
//
//            System.out.println(builder.toUriString());
//            ResponseEntity<String> responseEntity = restTemplate.exchange(
//                    builder.toUriString(),
//                    HttpMethod.GET,
//                    entity,
//                    String.class
//            );
    }

//    @GetMapping("/info")
//    public void initUserInfo(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(authentication.getName());
//        UserInfoDto userInfoDto = (UserInfoDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(userInfoDto.getEmail());
//    }

}
