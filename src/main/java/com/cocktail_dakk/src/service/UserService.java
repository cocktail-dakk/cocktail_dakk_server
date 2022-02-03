package com.cocktail_dakk.src.service;

import com.cocktail_dakk.config.BaseResponse;
import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.domain.user.UserInfoRepository;
import com.cocktail_dakk.src.domain.user.dto.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Transactional
@NoArgsConstructor
@Service
public class UserService {

    @Autowired
    UserInfoRepository userInfoRepository;
    UserInfo userInfo;

    //회원있없확인
    public UserInfoRes isInUserInfo(String deviceNum){
        List<UserInfo> findUser = userInfoRepository.findByDeviceNum(deviceNum);
        if(findUser.isEmpty()){
            return null;
        }
        //List의 요소를 조회하려면 get(index번호)로 하기
        return new UserInfoRes(findUser.get(0));
    }




    //회원가입(등록)
    public UserInfoRes signUpUser(UserInfoRes userSignUpReq){
        UserInfo userInfo = new UserInfo(userSignUpReq.getDeviceNum(),userSignUpReq.getNickname(),userSignUpReq.getAge(),
                userSignUpReq.getSex(),userSignUpReq.getAlcoholLevel(), Status.ACTIVE);
        UserInfo saveUser = userInfoRepository.save(userInfo);
        return new UserInfoRes(saveUser);
    }
}
