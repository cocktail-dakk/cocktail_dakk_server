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
public class UserController {

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @ResponseBody
    @GetMapping("/device-num") //device-num?deviceNum=1111
    public List<UserInfoRes> getUserInfoId(@RequestParam String deviceNum){
        return userService.isInUserInfo(deviceNum);
    }

    @ResponseBody
    @PostMapping("/sign-up")
    public Long signUp(@RequestParam String nickName){
        return 0L;//수정하기,,,,,인생,,,,
    }

}
