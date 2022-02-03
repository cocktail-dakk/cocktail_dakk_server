package com.cocktail_dakk.src.domain.cocktail.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetRecommendationListRes {
    private String tag; //키워드 or 기주
    private String description; //구체적인 명칭
    private List<GetRecommendationRes> recommendationRes;

    @Builder
    public GetRecommendationListRes(String tag, String description, List<GetRecommendationRes> recommendationRes) {
        this.tag = tag;
        this.description = description;
        this.recommendationRes = recommendationRes;
    }
}
