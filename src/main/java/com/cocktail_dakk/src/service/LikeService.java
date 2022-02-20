package com.cocktail_dakk.src.service;

import com.cocktail_dakk.config.BaseException;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfo;
import com.cocktail_dakk.src.domain.cocktail.CocktailInfoRepository;
import com.cocktail_dakk.src.domain.user.UserCocktail;
import com.cocktail_dakk.src.domain.user.UserCocktailRepository;
import com.cocktail_dakk.src.domain.user.UserInfo;
import com.cocktail_dakk.src.domain.user.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.cocktail_dakk.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final CocktailInfoRepository cocktailInfoRepository;
    private final UserCocktailRepository userCocktailRepository;
    private final UserInfoRepository userInfoRepository;

    @Transactional
    public void addLike(UserInfo userInfo, Long cocktailId) throws BaseException {

        CocktailInfo cocktailInfo = getCocktailInfo(cocktailId);

        if (userCocktailRepository.existsEventLikeByUserInfoAndCocktailInfo(userInfo, cocktailInfo)){
            throw new BaseException(DUPLICATE_LIKE);
        }

        UserCocktail userCocktail = UserCocktail.builder()
                .userInfo(userInfo)
                .cocktailInfo(cocktailInfo)
                .build();

        userCocktailRepository.save(userCocktail);
    }

    @Transactional
    public void deleteLike(UserInfo userInfo, Long cocktailId) throws BaseException {
        CocktailInfo cocktailInfo = getCocktailInfo(cocktailId);
        UserCocktail userCocktail = userCocktailRepository.findByUserInfoAndCocktailInfo(userInfo, cocktailInfo)
                .orElseThrow(() -> new BaseException(NOT_EXIST_USER_COCKTAIL));

        userCocktailRepository.deleteById(userCocktail.getUserCocktailId());

    }

    private CocktailInfo getCocktailInfo(Long cocktailId) throws BaseException {
        CocktailInfo cocktailInfo = cocktailInfoRepository.findById(cocktailId).orElseThrow(() -> new BaseException(NOT_EXIST_COCKTAIL));
        return cocktailInfo;
    }

    private UserInfo getUserInfo(Long userId) throws BaseException {
        UserInfo userInfo = userInfoRepository.findById(userId).orElseThrow(() -> new BaseException(NOT_EXIST_USER));
        return userInfo;
    }
}
