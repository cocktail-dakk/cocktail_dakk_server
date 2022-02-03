package com.cocktail_dakk.src.service;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.domain.user.UserInfoRepository;
import com.cocktail_dakk.src.domain.user.dto.UserInfoRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.cocktail_dakk.config.BaseResponseStatus.DATABASE_ERROR;
import static com.cocktail_dakk.config.BaseResponseStatus.NOT_EXIST_USER;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;

    @Transactional
    public UserInfo getUserInfo(String deviceNum) throws BaseException {

        return userInfoRepository.findByDeviceNum(deviceNum)
                .orElseThrow(() -> new BaseException(NOT_EXIST_USER));

    }
    //
    @Transactional
    public UserInfoRes signUpUser(UserInfoRes userSignUpReq) throws BaseException{
        try{
            UserInfo userInfo = new UserInfo(userSignUpReq.getDeviceNum(),userSignUpReq.getNickname(),userSignUpReq.getAge(),
                    userSignUpReq.getSex(),userSignUpReq.getAlcoholLevel(), Status.ACTIVE);
            UserInfo saveUser = userInfoRepository.save(userInfo);
            return new UserInfoRes(saveUser);



        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
