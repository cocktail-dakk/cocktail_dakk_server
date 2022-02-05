package com.cocktail_dakk.src.domain.mixingMethod;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMixingMethod is a Querydsl query type for MixingMethod
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMixingMethod extends EntityPathBase<MixingMethod> {

    private static final long serialVersionUID = 539182257L;

    public static final QMixingMethod mixingMethod = new QMixingMethod("mixingMethod");

    public final ListPath<com.cocktail_dakk.src.domain.cocktail.CocktailMixingMethod, com.cocktail_dakk.src.domain.cocktail.QCocktailMixingMethod> cocktailMixingMethods = this.<com.cocktail_dakk.src.domain.cocktail.CocktailMixingMethod, com.cocktail_dakk.src.domain.cocktail.QCocktailMixingMethod>createList("cocktailMixingMethods", com.cocktail_dakk.src.domain.cocktail.CocktailMixingMethod.class, com.cocktail_dakk.src.domain.cocktail.QCocktailMixingMethod.class, PathInits.DIRECT2);

    public final NumberPath<Long> mixingMethodId = createNumber("mixingMethodId", Long.class);

    public final StringPath mixingMethodName = createString("mixingMethodName");

    public final EnumPath<com.cocktail_dakk.src.domain.Status> status = createEnum("status", com.cocktail_dakk.src.domain.Status.class);

    public QMixingMethod(String variable) {
        super(MixingMethod.class, forVariable(variable));
    }

    public QMixingMethod(Path<? extends MixingMethod> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMixingMethod(PathMetadata metadata) {
        super(MixingMethod.class, metadata);
    }

}

