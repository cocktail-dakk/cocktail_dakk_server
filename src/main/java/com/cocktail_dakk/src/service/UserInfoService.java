package com.cocktail_dakk.src.service;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.drink.Drink;
import com.cocktail_dakk.src.domain.drink.DrinkRepository;
import com.cocktail_dakk.src.domain.keyword.Keyword;
import com.cocktail_dakk.src.domain.keyword.KeywordRepository;
import com.cocktail_dakk.src.domain.user.*;
import com.cocktail_dakk.src.domain.user.dto.UserInfoRes;
import com.cocktail_dakk.src.domain.user.dto.UserSignUpReq;
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
    private final UserKeywordRepository userKeywordRepository;
    private final KeywordRepository keywordRepository;
    private final DrinkRepository drinkRepository;
    private final UserDrinkRepository userDrinkRepository;

    @Transactional
    public UserInfo getUserInfo(String deviceNum) throws BaseException {

        return userInfoRepository.findByDeviceNum(deviceNum)
                .orElseThrow(() -> new BaseException(NOT_EXIST_USER));

    }
    //
    @Transactional
    public UserInfoRes signUpUser(UserSignUpReq userSignUpReq) throws BaseException{
        try{
            UserInfo userInfo = new UserInfo(userSignUpReq.getDeviceNum(),userSignUpReq.getNickname(),userSignUpReq.getAge(),
                    userSignUpReq.getSex(),userSignUpReq.getAlcoholLevel(), Status.ACTIVE);
            UserInfo saveUser = userInfoRepository.save(userInfo);

            String tempKeywords = userSignUpReq.getFavouritesKeywords();
            String[] tempKeywordArr = tempKeywords.split(",");

            String tempDrinks = userSignUpReq.getFavouritesDrinks();
            String[] tempDrinksArr = tempDrinks.split(",");

            for(int i=0;i<tempKeywordArr.length;i++){
                Keyword tempKeyword = keywordRepository.findByKeywordName(tempKeywordArr[i]);
                UserKeyword userKeyword = new UserKeyword(saveUser,tempKeyword);
                userKeywordRepository.save(userKeyword);

                Drink tempDrink = drinkRepository.findByDrinkName(tempDrinksArr[i]);
                UserDrink userDrink = new UserDrink(saveUser,tempDrink);
                userDrinkRepository.save(userDrink);


            }

            return new UserInfoRes(saveUser);

        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
