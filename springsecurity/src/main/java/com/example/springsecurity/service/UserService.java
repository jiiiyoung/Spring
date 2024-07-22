package com.example.springsecurity.service;


import com.example.springsecurity.dto.SignupRequestDto;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.entity.UserRoleEnum;
import com.example.springsecurity.jwt.JwtUtil;
import com.example.springsecurity.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    // ADMIN_TOKEN
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if(signupRequestDto.isAdmin()){
            if(!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)){
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, role);
        userRepository.save(user);

    }

    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if(!user.getPassword().equals(password)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));

    }

}
