package com.cocktail_dakk.src.domain.cocktail;

import com.cocktail_dakk.src.domain.Status;
import com.cocktail_dakk.src.domain.drink.QDrink;
import com.cocktail_dakk.src.domain.keyword.QKeyword;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class CocktailFilterRepositoryImpl implements CocktailFilterRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CocktailInfo> findSearchFilter(Pageable pageable, List<String> keywordName, Integer minAlcoholLevel, Integer maxAlcoholLevel, List<String> drinkName){
        QCocktailInfo qCocktailInfo=new QCocktailInfo("qCocktailInfo");

        QCocktailDrink qCocktailDrink=new QCocktailDrink("qCocktailDrink");
        QDrink qDrink=new QDrink("qDrink");

        QCocktailKeyword qCocktailKeyword=new QCocktailKeyword("qCocktailKeyword");
        QKeyword qKeyword=new QKeyword("qKeyword");

        return jpaQueryFactory.select(qCocktailInfo)
                .from(qCocktailInfo)
                .innerJoin(qCocktailInfo.cocktailKeywords, qCocktailKeyword)
                .innerJoin(qCocktailKeyword.keyword, qKeyword)
                .innerJoin(qCocktailInfo.cocktailDrinks, qCocktailDrink)
                .innerJoin(qCocktailDrink.drink, qDrink)
                .where(qKeyword.keywordName.in(keywordName).and(qDrink.drinkName.in(drinkName)).and(qCocktailInfo.alcoholLevel.between(minAlcoholLevel, maxAlcoholLevel)).and(qCocktailInfo.status.eq(Status.ACTIVE)))
                .distinct()
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();
    }
}
