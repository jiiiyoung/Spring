package com.example.logintest.domain.service;

import com.example.logintest.domain.dto.UserSignUpDto;

public interface UserService {
    long signUp(UserSignUpDto userSignUpDto);
}
