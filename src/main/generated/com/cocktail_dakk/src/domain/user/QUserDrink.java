package com.cocktail_dakk.src.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserDrink is a Querydsl query type for UserDrink
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserDrink extends EntityPathBase<UserDrink> {

    private static final long serialVersionUID = 594604775L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserDrink userDrink = new QUserDrink("userDrink");

    public final com.cocktail_dakk.src.domain.drink.QDrink drink;

    public final NumberPath<Long> userDrinkId = createNumber("userDrinkId", Long.class);

    public final QUserInfo userInfo;

    public QUserDrink(String variable) {
        this(UserDrink.class, forVariable(variable), INITS);
    }

    public QUserDrink(Path<? extends UserDrink> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserDrink(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserDrink(PathMetadata metadata, PathInits inits) {
        this(UserDrink.class, metadata, inits);
    }

    public QUserDrink(Class<? extends UserDrink> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.drink = inits.isInitialized("drink") ? new com.cocktail_dakk.src.domain.drink.QDrink(forProperty("drink")) : null;
        this.userInfo = inits.isInitialized("userInfo") ? new QUserInfo(forProperty("userInfo")) : null;
    }

}

