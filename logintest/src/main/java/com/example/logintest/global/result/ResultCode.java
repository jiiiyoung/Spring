package com.example.logintest.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResultCode {

    COMMON_OK(HttpStatus.OK, "c001", "요청 처리");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
