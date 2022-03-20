package com.cocktail_dakk.src.service;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfoRepository;
import com.cocktail_dakk.src.domain.cocktail.dto.SearchCocktailInfoRes;
import com.cocktail_dakk.src.domain.user.UserCocktail;
import com.cocktail_dakk.src.domain.user.UserCocktailRepository;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.domain.user.UserInfoRepository;
import com.cocktail_dakk.src.domain.user.dto.UserLikeRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

import static com.cocktail_dakk.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final CocktailInfoRepository cocktailInfoRepository;
    private final UserCocktailRepository userCocktailRepository;

    @Transactional
    public void addLike(UserInfo userInfo, Long cocktailId) throws BaseException {
        CocktailInfo cocktailInfo = getCocktailInfo(cocktailId);
        if (userCocktailRepository.existsLikeByUserInfoAndCocktailInfo(userInfo, cocktailInfo)){
            throw new BaseException(DUPLICATE_LIKE);
        }

        try{
            UserCocktail userCocktail = UserCocktail.builder()
                    .userInfo(userInfo)
                    .cocktailInfo(cocktailInfo)
                    .build();

            userCocktailRepository.save(userCocktail);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public void deleteLike(UserInfo userInfo, Long cocktailId) throws BaseException {
        CocktailInfo cocktailInfo = getCocktailInfo(cocktailId);
        UserCocktail userCocktail = userCocktailRepository.findByUserInfoAndCocktailInfo(userInfo, cocktailInfo)
                .orElseThrow(() -> new BaseException(NOT_EXIST_USER_COCKTAIL));

        try {
            userCocktailRepository.deleteById(userCocktail.getUserCocktailId());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public List<UserLikeRes> getUserLikes(UserInfo userInfo)throws BaseException{
        try {
            return userCocktailRepository.findByUserInfo(userInfo.getUserInfoId()).
                    stream()
                    .map(UserCocktail::getCocktailInfo)
                    .map(UserLikeRes::new)
                    .collect(Collectors.toList());

        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    private CocktailInfo getCocktailInfo(Long cocktailId) throws BaseException {
        CocktailInfo cocktailInfo = cocktailInfoRepository.findById(cocktailId).orElseThrow(() -> new BaseException(NOT_EXIST_COCKTAIL));
        return cocktailInfo;
    }

}
