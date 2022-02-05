package com.cocktail_dakk.src.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserInfo is a Querydsl query type for UserInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserInfo extends EntityPathBase<UserInfo> {

    private static final long serialVersionUID = 296420479L;

    public static final QUserInfo userInfo = new QUserInfo("userInfo");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final NumberPath<Integer> alcoholLevel = createNumber("alcoholLevel", Integer.class);

    public final StringPath deviceNum = createString("deviceNum");

    public final StringPath nickname = createString("nickname");

    public final StringPath sex = createString("sex");

    public final EnumPath<com.cocktail_dakk.src.domain.Status> status = createEnum("status", com.cocktail_dakk.src.domain.Status.class);

    public final ListPath<UserCocktail, QUserCocktail> userCocktails = this.<UserCocktail, QUserCocktail>createList("userCocktails", UserCocktail.class, QUserCocktail.class, PathInits.DIRECT2);

    public final ListPath<UserDrink, QUserDrink> userDrinks = this.<UserDrink, QUserDrink>createList("userDrinks", UserDrink.class, QUserDrink.class, PathInits.DIRECT2);

    public final NumberPath<Long> userInfoId = createNumber("userInfoId", Long.class);

    public final ListPath<UserKeyword, QUserKeyword> userKeywords = this.<UserKeyword, QUserKeyword>createList("userKeywords", UserKeyword.class, QUserKeyword.class, PathInits.DIRECT2);

    public QUserInfo(String variable) {
        super(UserInfo.class, forVariable(variable));
    }

    public QUserInfo(Path<? extends UserInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserInfo(PathMetadata metadata) {
        super(UserInfo.class, metadata);
    }

}

