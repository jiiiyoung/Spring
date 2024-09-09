package com.example.logintest.domain.controller;

import com.example.logintest.domain.dto.UserSignUpDto;
import com.example.logintest.domain.service.UserServiceImpl;
import com.example.logintest.global.result.ResultCode;
import com.example.logintest.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/sign-up")
    public ResponseEntity<ResultResponse> signUp(@RequestBody UserSignUpDto userSignUpDto) throws Exception {
        long result = userService.signUp(userSignUpDto);
        ResultResponse resultResponse = ResultResponse.of(ResultCode.COMMON_OK, result);
        return ResponseEntity.ok(resultResponse);
    }



}
