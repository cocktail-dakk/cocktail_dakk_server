package com.cocktail_dakk.src.domain.cocktail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCocktailInfo is a Querydsl query type for CocktailInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCocktailInfo extends EntityPathBase<CocktailInfo> {

    private static final long serialVersionUID = 1510763551L;

    public static final QCocktailInfo cocktailInfo = new QCocktailInfo("cocktailInfo");

    public final NumberPath<Integer> alcoholLevel = createNumber("alcoholLevel", Integer.class);

    public final StringPath cocktailBackgroundImageURL = createString("cocktailBackgroundImageURL");

    public final ListPath<CocktailDrink, QCocktailDrink> cocktailDrinks = this.<CocktailDrink, QCocktailDrink>createList("cocktailDrinks", CocktailDrink.class, QCocktailDrink.class, PathInits.DIRECT2);

    public final StringPath cocktailImageURL = createString("cocktailImageURL");

    public final NumberPath<Long> cocktailInfoId = createNumber("cocktailInfoId", Long.class);

    public final ListPath<CocktailKeyword, QCocktailKeyword> cocktailKeywords = this.<CocktailKeyword, QCocktailKeyword>createList("cocktailKeywords", CocktailKeyword.class, QCocktailKeyword.class, PathInits.DIRECT2);

    public final ListPath<CocktailMixingMethod, QCocktailMixingMethod> cocktailMixingMethods = this.<CocktailMixingMethod, QCocktailMixingMethod>createList("cocktailMixingMethods", CocktailMixingMethod.class, QCocktailMixingMethod.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final StringPath englishName = createString("englishName");

    public final StringPath ingredient = createString("ingredient");

    public final StringPath koreanName = createString("koreanName");

    public final NumberPath<java.math.BigDecimal> ratingAvg = createNumber("ratingAvg", java.math.BigDecimal.class);

    public final StringPath recommendImageURL = createString("recommendImageURL");

    public final StringPath smallNukkiImageURL = createString("smallNukkiImageURL");

    public final EnumPath<com.cocktail_dakk.src.domain.Status> status = createEnum("status", com.cocktail_dakk.src.domain.Status.class);

    public final ListPath<com.cocktail_dakk.src.domain.user.UserCocktail, com.cocktail_dakk.src.domain.user.QUserCocktail> userCocktails = this.<com.cocktail_dakk.src.domain.user.UserCocktail, com.cocktail_dakk.src.domain.user.QUserCocktail>createList("userCocktails", com.cocktail_dakk.src.domain.user.UserCocktail.class, com.cocktail_dakk.src.domain.user.QUserCocktail.class, PathInits.DIRECT2);

    public QCocktailInfo(String variable) {
        super(CocktailInfo.class, forVariable(variable));
    }

    public QCocktailInfo(Path<? extends CocktailInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCocktailInfo(PathMetadata metadata) {
        super(CocktailInfo.class, metadata);
    }

}

