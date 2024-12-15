package org.hae.sfaas.global.common.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access =  AccessLevel.PRIVATE)
public enum SuccessType {

    /**
     * 200 OK (2000 ~ 2099)
     */
    OK(HttpStatus.OK, 2000, "성공"),
    POST_LOGIN_SUCCESS(HttpStatus.OK, 2001, "사용자 로그인에 성공하였습니다"),
    GET_USERINFOS_SUCCESS(HttpStatus.OK, 2002, "사용자 정보 리스트 조회에 성공하였습니다"),
    PATCH_USERROLE_SUCCESS(HttpStatus.OK, 2003,"사용자 역할 수정에 성공하였습니다"),
    GET_MYINFO_SUCCESS(HttpStatus.OK, 2004, "내 정보 조회에 성공하였습니다" ),
    GET_WELDER_GATETIME_SUCCESS(HttpStatus.OK, 2005, "용접 길이에 따른 가동시간 추이 그래프 조회에 성공하였습니다" ),
    GET_WELDER_DETAIL_SUCCESS(HttpStatus.OK, 2006, "용접 설비 데이터 조회에 성공하였습니다"),
    GET_WELDER_STATUS_SUCCESS(HttpStatus.OK, 2007, "용접 설비 불량률 조회에 성공하였습니다"),
    GET_WELDER_POWER_SUCCESS(HttpStatus.OK, 2008, "용접 설비 출력값 조회에 성공하였습니다");

    /**
     * 201 CREATED (2100 ~ 2199)
     */


    /**
     * 204 NO CONTENT (2400 ~ 2499)
     */

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

}


