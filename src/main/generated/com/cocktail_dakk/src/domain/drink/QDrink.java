package com.cocktail_dakk.src.domain.drink;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDrink is a Querydsl query type for Drink
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDrink extends EntityPathBase<Drink> {

    private static final long serialVersionUID = 270778085L;

    public static final QDrink drink = new QDrink("drink");

    public final ListPath<com.cocktail_dakk.src.domain.cocktail.CocktailDrink, com.cocktail_dakk.src.domain.cocktail.QCocktailDrink> cocktailDrinks = this.<com.cocktail_dakk.src.domain.cocktail.CocktailDrink, com.cocktail_dakk.src.domain.cocktail.QCocktailDrink>createList("cocktailDrinks", com.cocktail_dakk.src.domain.cocktail.CocktailDrink.class, com.cocktail_dakk.src.domain.cocktail.QCocktailDrink.class, PathInits.DIRECT2);

    public final NumberPath<Long> drinkId = createNumber("drinkId", Long.class);

    public final StringPath drinkName = createString("drinkName");

    public final EnumPath<com.cocktail_dakk.src.domain.Status> status = createEnum("status", com.cocktail_dakk.src.domain.Status.class);

    public QDrink(String variable) {
        super(Drink.class, forVariable(variable));
    }

    public QDrink(Path<? extends Drink> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDrink(PathMetadata metadata) {
        super(Drink.class, metadata);
    }

}

