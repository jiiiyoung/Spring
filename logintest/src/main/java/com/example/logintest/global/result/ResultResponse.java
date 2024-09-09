package com.example.logintest.global.result;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ResultResponse {

    private final HttpStatus status;
    private final String message;
    private final String code;
    private final Object data;


    public static ResultResponse of(ResultCode resultCode, Object data) {
        return ResultResponse.builder()
                .status(resultCode.getStatus())
                .code(resultCode.getCode())
                .data(data)
                .message(resultCode.getMessage())
                .build();
    }

    public static ResultResponse of(ResultCode resultCode) {
        return ResultResponse.builder()
                .status(resultCode.getStatus())
                .code(resultCode.getCode())
                .message(resultCode.getMessage())
                .build();
    }
}
