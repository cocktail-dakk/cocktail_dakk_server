package com.cocktail_dakk.src.controller;

import com.cocktail_dakk.src.domain.user.dto.UserInfoRes;
import com.cocktail_dakk.src.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @ResponseBody
    @GetMapping("/device-num") //device-num?deviceNum=1111
    public UserInfoRes getUserInfoId(@RequestParam String deviceNum){
        return userService.isInUserInfo(deviceNum); //회원없-null, 회원있-회원 전체 정보 반환
    }

    @ResponseBody
    @PostMapping("/sign-up") //json에 담아서
    public UserInfoRes signUp(@RequestBody UserInfoRes userInfoRes){
        return userService.signUpUser(userInfoRes); //회원가입하면 회원 전체 정보 반환
    }



}
