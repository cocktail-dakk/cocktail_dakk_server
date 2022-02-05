package com.cocktail_dakk.src.service;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.drink.Drink;
import com.cocktail_dakk.src.domain.drink.DrinkRepository;
import com.cocktail_dakk.src.domain.keyword.Keyword;
import com.cocktail_dakk.src.domain.keyword.KeywordRepository;
import com.cocktail_dakk.src.domain.user.*;
import com.cocktail_dakk.src.domain.user.dto.UserInfoRes;
import com.cocktail_dakk.src.domain.user.dto.UserModifyReq;
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

            addFavourites(userSignUpReq.getFavouritesKeywords(), userSignUpReq.getFavouritesDrinks(),saveUser);

            return new UserInfoRes(saveUser);

        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public UserInfoRes modifyUser(UserModifyReq userModifyReq) throws BaseException{
        try {
            UserInfo userInfo = getUserInfo(userModifyReq.getDeviceNum());
            userInfo.updateUser(userModifyReq.getNickname(), userModifyReq.getAlcoholLevel());

            addFavourites(userModifyReq.getFavouritesKeywords(),userModifyReq.getFavouritesDrinks(),userInfo);

            return new UserInfoRes(userInfo);
        } catch (Exception e){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public void addFavourites(String favouritesKeywords,String favouritesDrinks, UserInfo userInfo){
        String[] tempKeywordArr = favouritesKeywords.split(",");
        String[] tempDrinksArr = favouritesDrinks.split(",");

        // userDrink에 값이 있으면 delete하고 시작
        if(!userInfo.getUserDrinks().isEmpty()){
            userDrinkRepository.deleteAll(userInfo.getUserDrinks());
        }
        // userKeyword에 값이 있으면 delete하고 시작
        if(!userInfo.getUserKeywords().isEmpty()){
            userKeywordRepository.deleteAll(userInfo.getUserKeywords());
        }
        //keyword 하나씩 저장
        for(String keyword:tempKeywordArr){
            Keyword tempKeyword = keywordRepository.findByKeywordName(keyword);
            UserKeyword userKeyword = new UserKeyword(userInfo,tempKeyword);
            userKeywordRepository.save(userKeyword);
        }
        //Drink 하나씩 저장
        for(String drink:tempDrinksArr){
            Drink tempDrink = drinkRepository.findByDrinkName(drink);
            UserDrink userDrink = new UserDrink(userInfo,tempDrink);
            userDrinkRepository.save(userDrink);
        }
    }

}
