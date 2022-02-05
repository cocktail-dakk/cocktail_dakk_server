package com.cocktail_dakk.src.domain.keyword;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QKeyword is a Querydsl query type for Keyword
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QKeyword extends EntityPathBase<Keyword> {

    private static final long serialVersionUID = -648743801L;

    public static final QKeyword keyword = new QKeyword("keyword");

    public final ListPath<com.cocktail_dakk.src.domain.cocktail.CocktailKeyword, com.cocktail_dakk.src.domain.cocktail.QCocktailKeyword> cocktailKeywords = this.<com.cocktail_dakk.src.domain.cocktail.CocktailKeyword, com.cocktail_dakk.src.domain.cocktail.QCocktailKeyword>createList("cocktailKeywords", com.cocktail_dakk.src.domain.cocktail.CocktailKeyword.class, com.cocktail_dakk.src.domain.cocktail.QCocktailKeyword.class, PathInits.DIRECT2);

    public final NumberPath<Long> keywordId = createNumber("keywordId", Long.class);

    public final StringPath keywordName = createString("keywordName");

    public QKeyword(String variable) {
        super(Keyword.class, forVariable(variable));
    }

    public QKeyword(Path<? extends Keyword> path) {
        super(path.getType(), path.getMetadata());
    }

    public QKeyword(PathMetadata metadata) {
        super(Keyword.class, metadata);
    }

}

