package com.example.logintest.global.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.logintest.domain.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class JwtService {
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.access.expiration}")
    private Long accessTokenExpirationPeriod;

    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpirationPeriod;

    @Value("${jwt.access.header}")
    private String accessHeader;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;

    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";

    /*
    Claim: 토큰에서 사용할 정보
    민감하지 않은 식별을 위한 정보를 담아야한다.

     JWT의 Subject와 Claim으로 email사용
     우리는 아마 userId, role이지 않을까 싶음
    */
    private static final String EMAIL_CLAIM = "email";
    private static final String BEARER = "Bearer ";

    private final UserRepository userRepository;

    public String createAccessToken(String email) {
        Date now = new Date();
        return JWT.create()
                .withSubject(ACCESS_TOKEN_SUBJECT)
                .withExpiresAt(new Date(now.getTime() + accessTokenExpirationPeriod))
                .withClaim(EMAIL_CLAIM, email)
                .sign(Algorithm.HMAC512(secretKey));
    }

    /*
    * RefreshToken 생성
    * 리프레시토큰에는 클레임에 정보를 넣지 않을 것이기 때문에 withClaim()은 X
    * */
    public String createRefreshToken() {
        Date now = new Date();
        return JWT.create()
                .withSubject(REFRESH_TOKEN_SUBJECT)
                .withExpiresAt(new Date(now.getTime() + refreshTokenExpirationPeriod))
                .sign(Algorithm.HMAC512(secretKey));
    }

    /*
    * AccessToken 헤더에 실어서 보내기
    * */
    public void sendAccessToken(HttpServletResponse response, String accessToken){
        response.setStatus(HttpServletResponse.SC_OK);

        response.setHeader(accessHeader, accessToken);
        log.info("재발급된 Access Token : {}", accessToken);
    }

    /*
    * AccessToken + RefreshToken 헤더에 실어서 보내기
    * */
    public void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken){
        response.setStatus(HttpServletResponse.SC_OK);

        setAccessTokenHeader(response, accessToken); // response.setHeader
        setRefreshTokenHeader(response, refreshToken);

        log.info("Access Token, Refresh Token 헤더 설정 완료");

    }

    /*
    * 헤더에서 RefreshToken 추출
    * 토큰 형식: Bearer 를 제외하고 순수 토큰만 가져오기 위해서 헤더를 가져온 후 삭제
    * */
    public Optional<String> extractRefreshToken(HttpServletRequest request){
        return Optional.ofNullable(request.getHeader(refreshHeader)) // 요청의 헤더에서
                .filter(refreshToken -> refreshToken.startsWith(BEARER)) // BEARER로 시작하는 것만 걸러서
                .map(refreshToken -> refreshToken.replace(BEARER, "")); // BEARER를 제외하고 반환
    }




}

