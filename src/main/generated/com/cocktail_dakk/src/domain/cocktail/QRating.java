package com.cocktail_dakk.src.domain.cocktail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRating is a Querydsl query type for Rating
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRating extends EntityPathBase<Rating> {

    private static final long serialVersionUID = -1624324982L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRating rating1 = new QRating("rating1");

    public final QCocktailInfo cocktailInfo;

    public final NumberPath<java.math.BigDecimal> rating = createNumber("rating", java.math.BigDecimal.class);

    public final NumberPath<Long> ratingId = createNumber("ratingId", Long.class);

    public final com.cocktail_dakk.src.domain.user.QUserInfo userInfo;

    public QRating(String variable) {
        this(Rating.class, forVariable(variable), INITS);
    }

    public QRating(Path<? extends Rating> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRating(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRating(PathMetadata metadata, PathInits inits) {
        this(Rating.class, metadata, inits);
    }

    public QRating(Class<? extends Rating> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cocktailInfo = inits.isInitialized("cocktailInfo") ? new QCocktailInfo(forProperty("cocktailInfo")) : null;
        this.userInfo = inits.isInitialized("userInfo") ? new com.cocktail_dakk.src.domain.user.QUserInfo(forProperty("userInfo")) : null;
    }

}

