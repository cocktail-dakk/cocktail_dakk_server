package com.cocktail_dakk.src.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserKeyword is a Querydsl query type for UserKeyword
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserKeyword extends EntityPathBase<UserKeyword> {

    private static final long serialVersionUID = 1744969848L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserKeyword userKeyword = new QUserKeyword("userKeyword");

    public final com.cocktail_dakk.src.domain.keyword.QKeyword keyword;

    public final QUserInfo userInfo;

    public final NumberPath<Long> userKeywordId = createNumber("userKeywordId", Long.class);

    public QUserKeyword(String variable) {
        this(UserKeyword.class, forVariable(variable), INITS);
    }

    public QUserKeyword(Path<? extends UserKeyword> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserKeyword(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserKeyword(PathMetadata metadata, PathInits inits) {
        this(UserKeyword.class, metadata, inits);
    }

    public QUserKeyword(Class<? extends UserKeyword> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.keyword = inits.isInitialized("keyword") ? new com.cocktail_dakk.src.domain.keyword.QKeyword(forProperty("keyword")) : null;
        this.userInfo = inits.isInitialized("userInfo") ? new QUserInfo(forProperty("userInfo")) : null;
    }

}

