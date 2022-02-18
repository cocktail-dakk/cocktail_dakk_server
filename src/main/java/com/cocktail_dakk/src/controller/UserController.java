package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.BaseResponse;
import com.cocktail_dakk.config.auth.CurrentUser;
import com.cocktail_dakk.config.auth.dto.UserInfoDto;
import com.cocktail_dakk.src.domain.user.dto.UserInfoReq;
import com.cocktail_dakk.src.domain.user.dto.UserInfoRes;
import com.cocktail_dakk.src.domain.user.dto.UserModifyReq;
import com.cocktail_dakk.src.domain.user.dto.UserSignUpReq;
import com.cocktail_dakk.src.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserInfoService userService;


//    @ResponseBody
//    @GetMapping("/device-num") //device-num?deviceNum=1111
//    public BaseResponse<UserInfoRes> getUserInfoId(@RequestParam String deviceNum){
//        try {
//            return new BaseResponse<>(new UserInfoRes(userService.getUserInfo(deviceNum)));
//        } catch(BaseException e){
//            return new BaseResponse<>(e.getStatus());
//        }//회원없-null, 회원있-회원 전체 정보 반환
//    }

//    @ResponseBody
//    @PostMapping("/sign-up") //json에 담아서
//    public BaseResponse<UserInfoRes> signUp(@RequestBody UserSignUpReq userSignUpReq){
//        try {
//            return new BaseResponse<>(userService.signUpUser(userSignUpReq));
//        } catch (BaseException e){
//            return new BaseResponse<>(e.getStatus());
//        }//회원가입하면 회원 전체 정보 반환
//    }

//    @PatchMapping("/modify")
//    public BaseResponse<UserInfoRes> modifyUser(@RequestBody UserModifyReq userModifyReq){
//        try {
//            return new BaseResponse<>(userService.modifyUser(userModifyReq));
//        }catch (BaseException e) {
//            return new BaseResponse<>(e.getStatus());
//        }
//    }

    @GetMapping("/login")
    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }

}
