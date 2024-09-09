package com.example.logintest.domain.service;

import com.example.logintest.domain.dto.UserSignUpDto;
import com.example.logintest.domain.entity.User;
import com.example.logintest.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public long signUp(UserSignUpDto userSignUpDto) throws Exception {
        if(userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()){
            throw new Exception("중복된 이메일 입니다.");
        }

        if(userRepository.findByNickname(userSignUpDto.getNickname()).isPresent()) {
            throw new Exception("중복된 닉네임 입니다.");
        }

        User user = User.builder()
                .email(userSignUpDto.getEmail())
                .nickname(userSignUpDto.getNickname())
                .password(userSignUpDto.getPassword()) // 일반 텍스트로 객체를 우선 생성.
                                                        // 이렇게 하면 비밀번호 인코딩 전에 다른 필드 유효성 검사를 할 수 있다.
                .age(userSignUpDto.getAge())
                .city(userSignUpDto.getCity())
                .build();

        user.passwordEncode(passwordEncoder);
        userRepository.save(user);

        return user.getId();
    }

}
