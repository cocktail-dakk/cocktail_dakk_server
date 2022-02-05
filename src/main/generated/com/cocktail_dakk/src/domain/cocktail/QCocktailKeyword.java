package com.cocktail_dakk.src.domain.cocktail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCocktailKeyword is a Querydsl query type for CocktailKeyword
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCocktailKeyword extends EntityPathBase<CocktailKeyword> {

    private static final long serialVersionUID = 1729893592L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCocktailKeyword cocktailKeyword = new QCocktailKeyword("cocktailKeyword");

    public final QCocktailInfo cocktailInfo;

    public final NumberPath<Long> cocktailKeywordId = createNumber("cocktailKeywordId", Long.class);

    public final com.cocktail_dakk.src.domain.keyword.QKeyword keyword;

    public QCocktailKeyword(String variable) {
        this(CocktailKeyword.class, forVariable(variable), INITS);
    }

    public QCocktailKeyword(Path<? extends CocktailKeyword> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCocktailKeyword(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCocktailKeyword(PathMetadata metadata, PathInits inits) {
        this(CocktailKeyword.class, metadata, inits);
    }

    public QCocktailKeyword(Class<? extends CocktailKeyword> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cocktailInfo = inits.isInitialized("cocktailInfo") ? new QCocktailInfo(forProperty("cocktailInfo")) : null;
        this.keyword = inits.isInitialized("keyword") ? new com.cocktail_dakk.src.domain.keyword.QKeyword(forProperty("keyword")) : null;
    }

}

