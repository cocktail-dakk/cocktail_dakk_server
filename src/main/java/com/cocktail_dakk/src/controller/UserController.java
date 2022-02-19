package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.BaseResponse;
import com.cocktail_dakk.src.domain.user.dto.UserInfoRes;
import com.cocktail_dakk.src.domain.user.dto.UserModifyReq;
import com.cocktail_dakk.src.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserInfoService userService;


    @ResponseBody
    @GetMapping("/info") //device-num?deviceNum=1111
    public BaseResponse<UserInfoRes> getUserInfoId(){
        try {
            return new BaseResponse<>(new UserInfoRes(userService.getUserInfo()));
        } catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }//회원없-null, 회원있-회원 전체 정보 반환
    }

    @PatchMapping("/modify")
    public BaseResponse<UserInfoRes> modifyUser(@RequestBody UserModifyReq userModifyReq){
        try {
            return new BaseResponse<>(userService.modifyUser(userModifyReq));
        }catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @GetMapping("/login")
    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }

//    @GetMapping("/info")
//    public void initUserInfo(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(authentication.getName());
//        UserInfoDto userInfoDto = (UserInfoDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(userInfoDto.getEmail());
//    }

}
