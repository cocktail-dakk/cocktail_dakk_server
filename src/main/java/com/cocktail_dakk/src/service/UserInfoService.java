package com.cocktail_dakk.src.service;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.domain.user.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.cocktail_dakk.config.BaseResponseStatus.NOT_EXIST_USER;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;

    public UserInfo getUserInfo(String deviceNum) throws BaseException {

        return userInfoRepository.findByDeviceNum(deviceNum)
                .orElseThrow(() -> new BaseException(NOT_EXIST_USER));

    }

}
