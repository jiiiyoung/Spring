package com.example.logintest.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

// 자체 로그인할 때 받을 DTO
@NoArgsConstructor
@Getter
public class UserSignUpDto {
    private String email;
    private String password;
    private String nickname;
    private int age;
    private String city;
}
