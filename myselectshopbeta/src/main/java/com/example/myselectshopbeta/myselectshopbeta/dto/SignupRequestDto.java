package com.example.myselectshopbeta.myselectshopbeta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    private String username;
    private String password;
    private String email;
    private boolean admin = false; // 기본 값이 false이여야한다.
    private String adminToken;


}
