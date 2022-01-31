package com.cocktail_dakk.src.service;

import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.domain.user.UserInfoRepository;
import com.cocktail_dakk.src.domain.user.dto.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@NoArgsConstructor
@Service
public class UserService {

    @Autowired
    UserInfoRepository userInfoRepository;
    UserInfo userInfo;

    //회원있없확인
    public List<UserInfoRes> isInUserInfo(String deviceNum){
        List<UserInfoRes> findUser = userInfoRepository.findByDeviceNum(deviceNum);
        if(findUser.isEmpty()){
            return null;
        }
        //List의 요소를 조회하려면 get(index번호)로 하기
        return findUser;
    }




    //회원가입(등록)
    public UserInfoRes signUpUser(UserInfoRes userSignUpReq){
        UserInfoRes saveUser = userInfoRepository.save(userSignUpReq);
        return saveUser;

    }
}
