package com.example.logintest.domain.repository;

import com.example.logintest.domain.entity.SocialType;
import com.example.logintest.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmail(String email);
    Optional<User> findByRefreshToken(String refreshToken);

    /*
    * 소셜 타입과 소셜의 식별값으로 회원 찾는 메소드
    * 추가 정보를 입력받아야 하기 때문에 소셜 회원가입 한 회원을 찾는 메소드
    * */
    Optional<User> findBySocialTypeAndSocialId(SocialType socialType, String socialId);

}
