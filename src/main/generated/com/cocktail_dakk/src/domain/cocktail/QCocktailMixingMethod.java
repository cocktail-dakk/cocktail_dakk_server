package com.cocktail_dakk.src.domain.cocktail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCocktailMixingMethod is a Querydsl query type for CocktailMixingMethod
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCocktailMixingMethod extends EntityPathBase<CocktailMixingMethod> {

    private static final long serialVersionUID = -1080677640L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCocktailMixingMethod cocktailMixingMethod = new QCocktailMixingMethod("cocktailMixingMethod");

    public final QCocktailInfo cocktailInfo;

    public final NumberPath<Long> cocktailMixingMethodId = createNumber("cocktailMixingMethodId", Long.class);

    public final com.cocktail_dakk.src.domain.mixingMethod.QMixingMethod mixingMethod;

    public QCocktailMixingMethod(String variable) {
        this(CocktailMixingMethod.class, forVariable(variable), INITS);
    }

    public QCocktailMixingMethod(Path<? extends CocktailMixingMethod> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCocktailMixingMethod(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCocktailMixingMethod(PathMetadata metadata, PathInits inits) {
        this(CocktailMixingMethod.class, metadata, inits);
    }

    public QCocktailMixingMethod(Class<? extends CocktailMixingMethod> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cocktailInfo = inits.isInitialized("cocktailInfo") ? new QCocktailInfo(forProperty("cocktailInfo")) : null;
        this.mixingMethod = inits.isInitialized("mixingMethod") ? new com.cocktail_dakk.src.domain.mixingMethod.QMixingMethod(forProperty("mixingMethod")) : null;
    }

}

