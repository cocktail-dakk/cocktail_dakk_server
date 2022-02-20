package com.cocktail_dakk.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),
    NOT_EXIST_USER(false,2004,"존재하지 않는 회원입니다."),

    POST_EXISTS_RATING(false,2005,"이미 평가한 칵테일입니다."),
    POST_RATING_EMPTY(false, 2006, "내용을 입력해주세요."),
    NOT_EXIST_COCKTAIL(false,2007,"존재하지 않는 칵테일입니다."),
    STAR_OUT_OF_RANGE(false,2008,"별점을 확인해주세요."),
    POST_KEYWORD_EMPTY(false,2009,"칵테일 취향을 입력해주세요"),
    POST_DRINK_EMPTY(false,2010,"칵테일 기주를 입력해주세요"),
    DUPLICATE_LIKE(false,2011,"이미 존재하는 좋아요입니다."),
    DUPLICATE_UNLIKE(false,2012,"이미 좋아요 취소를 하였습니다."),
    NOT_EXIST_USER_COCKTAIL(false,2013,"존재하지 좋아요 목록입니다."),

    EXIST_USER(false,2023,"이미 존재하는 회원입니다."),


    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),



    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다.");

    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}