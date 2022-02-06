package com.cocktail_dakk.src.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserCocktail is a Querydsl query type for UserCocktail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserCocktail extends EntityPathBase<UserCocktail> {

    private static final long serialVersionUID = 1141188469L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserCocktail userCocktail = new QUserCocktail("userCocktail");

    public final com.cocktail_dakk.src.domain.cocktail.QCocktailInfo cocktailInfo;

    public final NumberPath<Long> userCocktailId = createNumber("userCocktailId", Long.class);

    public final QUserInfo userInfo;

    public QUserCocktail(String variable) {
        this(UserCocktail.class, forVariable(variable), INITS);
    }

    public QUserCocktail(Path<? extends UserCocktail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserCocktail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserCocktail(PathMetadata metadata, PathInits inits) {
        this(UserCocktail.class, metadata, inits);
    }

    public QUserCocktail(Class<? extends UserCocktail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cocktailInfo = inits.isInitialized("cocktailInfo") ? new com.cocktail_dakk.src.domain.cocktail.QCocktailInfo(forProperty("cocktailInfo")) : null;
        this.userInfo = inits.isInitialized("userInfo") ? new QUserInfo(forProperty("userInfo")) : null;
    }

}

