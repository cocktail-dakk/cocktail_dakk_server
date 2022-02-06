package com.cocktail_dakk.src.domain.cocktail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCocktailDrink is a Querydsl query type for CocktailDrink
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCocktailDrink extends EntityPathBase<CocktailDrink> {

    private static final long serialVersionUID = -415465657L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCocktailDrink cocktailDrink = new QCocktailDrink("cocktailDrink");

    public final NumberPath<Long> cocktailDrinkId = createNumber("cocktailDrinkId", Long.class);

    public final QCocktailInfo cocktailInfo;

    public final com.cocktail_dakk.src.domain.drink.QDrink drink;

    public QCocktailDrink(String variable) {
        this(CocktailDrink.class, forVariable(variable), INITS);
    }

    public QCocktailDrink(Path<? extends CocktailDrink> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCocktailDrink(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCocktailDrink(PathMetadata metadata, PathInits inits) {
        this(CocktailDrink.class, metadata, inits);
    }

    public QCocktailDrink(Class<? extends CocktailDrink> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cocktailInfo = inits.isInitialized("cocktailInfo") ? new QCocktailInfo(forProperty("cocktailInfo")) : null;
        this.drink = inits.isInitialized("drink") ? new com.cocktail_dakk.src.domain.drink.QDrink(forProperty("drink")) : null;
    }

}

