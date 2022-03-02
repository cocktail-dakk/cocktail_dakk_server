package com.cocktail_dakk.src.service;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.config.auth.dto.UserInfoDto;
import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.drink.Drink;
import com.cocktail_dakk.src.domain.drink.DrinkRepository;
import com.cocktail_dakk.src.domain.keyword.Keyword;
import com.cocktail_dakk.src.domain.keyword.KeywordRepository;
import com.cocktail_dakk.src.domain.user.*;
import com.cocktail_dakk.src.domain.user.dto.UserInfoReq;
import com.cocktail_dakk.src.domain.user.dto.UserInfoRes;
import com.cocktail_dakk.src.domain.user.dto.UserInfoStatusRes;
import com.cocktail_dakk.src.domain.user.dto.UserModifyReq;
//import com.cocktail_dakk.src.domain.user.dto.UserSignUpReq;
import com.cocktail_dakk.src.domain.user.projection.UserInfoStatusProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.cocktail_dakk.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final UserKeywordRepository userKeywordRepository;
    private final KeywordRepository keywordRepository;
    private final DrinkRepository drinkRepository;
    private final UserDrinkRepository userDrinkRepository;

    public UserInfo getUserInfo() throws BaseException {
        UserInfoDto userInfoDto = (UserInfoDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userInfoRepository.findByEmail(userInfoDto.getEmail())
                .orElseThrow(() -> new BaseException(NOT_EXIST_USER));
    }

    public UserInfoStatusRes getUserInfoStatus() throws BaseException{
        try {
            UserInfoDto userInfoDto = (UserInfoDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserInfoStatusProjection userInfoStatusProjection = userInfoRepository.findStatusByEmail(userInfoDto.getEmail())
                    .orElseThrow(() -> new BaseException(NOT_EXIST_USER));

            Status status = userInfoStatusProjection.getStatus();
            if(status==null||status==Status.INACTIVE){
                return new UserInfoStatusRes(userInfoStatusProjection.getEmail(), true);
            }else{
                return new UserInfoStatusRes(userInfoStatusProjection.getEmail(), false);
            }
        }catch (BaseException e){
            throw new BaseException(e.getStatus());
        }
    }

    @Transactional
    public UserInfoRes saveOrUpdate(UserInfoDto userInfoDto) throws BaseException{
        try {
            UserInfo userInfo = userInfoRepository.findByEmail(userInfoDto.getEmail()).orElse(userInfoDto.toEntity());
            UserInfo save = userInfoRepository.save(userInfo);

            return new UserInfoRes(save);
        }catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    @Transactional
    public UserInfoRes initUser(UserInfoReq userInfoReq) throws BaseException{
        try {
            UserInfo userInfo = getUserInfo();
            userInfo.initUserInfo(userInfoReq.getNickname(), userInfoReq.getAge(), userInfoReq.getSex(), userInfoReq.getAlcoholLevel(), Status.ACTIVE);

            addFavourites(userInfoReq.getFavouritesKeywords(),userInfoReq.getFavouritesDrinks(), userInfo);

            return new UserInfoRes(userInfo);
        } catch (BaseException e){
            throw new BaseException(e.getStatus());
        }
    }

    @Transactional
    public UserInfoRes modifyUser(UserModifyReq userModifyReq) throws BaseException{
        try {
            UserInfo userInfo = getUserInfo();
            userInfo.updateUser(userModifyReq.getNickname(), userModifyReq.getAlcoholLevel());

            addFavourites(userModifyReq.getFavouritesKeywords(),userModifyReq.getFavouritesDrinks(),userInfo);

            return new UserInfoRes(userInfo);
        } catch (BaseException e){
            throw new BaseException(e.getStatus());
        }
    }

    private void addFavourites(String favouritesKeywords,String favouritesDrinks, UserInfo userInfo) throws BaseException {

        if(favouritesKeywords.isEmpty()){
            throw new BaseException(POST_KEYWORD_EMPTY);
        }
        if(favouritesDrinks.isEmpty()){
            throw new BaseException(POST_DRINK_EMPTY);
        }

        String[] tempKeywordArr = favouritesKeywords.split(",");
        String[] tempDrinksArr = favouritesDrinks.split(",");

        try {
            // userDrink에 값이 있으면 delete하고 시작
            if(!userInfo.getUserDrinks().isEmpty()){
                userDrinkRepository.deleteAll(userInfo.getUserDrinks());
            }
            // userKeyword에 값이 있으면 delete하고 시작
            if(!userInfo.getUserKeywords().isEmpty()){
                userKeywordRepository.deleteAll(userInfo.getUserKeywords());
            }

            userInfo.getUserKeywords().clear();
            userInfo.getUserDrinks().clear();

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
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
