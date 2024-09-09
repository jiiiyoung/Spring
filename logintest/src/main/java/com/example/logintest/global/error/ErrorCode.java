package com.example.logintest.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    COMMON_ETC(HttpStatus.INTERNAL_SERVER_ERROR, "c001", "서버에 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
