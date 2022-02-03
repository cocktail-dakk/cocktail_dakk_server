package com.cocktail_dakk.src.domain.cocktail.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetUserRecommendationRes {
    private String nickname;
    private List<UserRecommendationList> userRecommendationLists;

    @Builder
    public GetUserRecommendationRes(String nickname, List<UserRecommendationList> userRecommendationLists) {
        this.nickname = nickname;
        this.userRecommendationLists = userRecommendationLists;
    }
}
