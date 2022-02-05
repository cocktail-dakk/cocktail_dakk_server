package com.cocktail_dakk.src.domain.cocktail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCocktailToday is a Querydsl query type for CocktailToday
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCocktailToday extends EntityPathBase<CocktailToday> {

    private static final long serialVersionUID = -400783888L;

    public static final QCocktailToday cocktailToday = new QCocktailToday("cocktailToday");

    public final NumberPath<Long> cocktailTodayId = createNumber("cocktailTodayId", Long.class);

    public final ListPath<Long, NumberPath<Long>> randomId = this.<Long, NumberPath<Long>>createList("randomId", Long.class, NumberPath.class, PathInits.DIRECT2);

    public QCocktailToday(String variable) {
        super(CocktailToday.class, forVariable(variable));
    }

    public QCocktailToday(Path<? extends CocktailToday> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCocktailToday(PathMetadata metadata) {
        super(CocktailToday.class, metadata);
    }

}

