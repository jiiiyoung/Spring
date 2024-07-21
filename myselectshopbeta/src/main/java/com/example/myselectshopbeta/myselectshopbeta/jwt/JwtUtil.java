package com.example.myselectshopbeta.myselectshopbeta.jwt;

import com.example.myselectshopbeta.myselectshopbeta.entity.UserRoleEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    /*
    JWT는 세 부분으로 구성된다. 헤더(Header), 페이로드(Payload), 서명(Signature)
    1. Header(헤더) : 토큰 타입(JWT)과 해싱 알고리즘(HMAC SHA256 또는 RSA) 정보를 포함
    2. Payload(페이로드) : 클레임을 포함하는 부분으로, 사용자에 대한 정보나 토큰의 메타데이터를 포함
    3. Signature(서명) : 헤더와 페이로드를 인코딩 한 후, 비밀키를 사용하여 생성된 서명

    클레임 : 등록된 클레임, 공개 클레임, 비공개 클레임
    1. 등록된 클레임(Registerd Claims)
        JWT 표준에서 정의된, 일반적으로 사용되는 클레임
        - iss (Issuer): 토큰을 발급한 주체를 식별합니다.
        - sub (Subject): 토큰의 주체를 식별합니다. 일반적으로 사용자 ID나 사용자 이름을 사용합니다.
        - aud (Audience): 토큰의 대상 수신자를 식별합니다.
        - exp (Expiration Time): 토큰의 만료 시간을 나타냅니다. 이 시간이 지나면 토큰은 유효하지 않게 됩니다.
        - nbf (Not Before): 이 시간이 되기 전에는 토큰이 유효하지 않음을 나타냅니다.
        - iat (Issued At): 토큰이 발급된 시간을 나타냅니다.
        - jti (JWT ID): 토큰의 고유 식별자를 나타냅니다.
    2. 공개 클레임(Public Claims)
        이메일 주소나 사용자 역할 같은 데이터를 담고 있다.
    3. 비공개 클레임(Private Claims)
        발급자와 수신자 간에 협의된 클레임, 표준이나 공개 클레임에 속하지 않는 사용자 정의 클레임
        특정 애플리케이션의 요구사항을 충족하기 위해 사용
    */


    // Header KEY 값
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // 사용자 권한 값의 KEY
    public static final String AUTHORIZATION_KEY = "auth";

    // Token 식별자
    private static final String BEARER_PREFIX = "Bearer ";

    // 토큰 만료시간
    private static final long TOKEN_TIME = 60 * 60 * 1000L;

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);

    }

    // header에서 토큰 가져오기
    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)){
            return bearerToken.substring(7); // "bearer "이 7글자이므로 7번 index부터 토큰
        }
        // 토큰 없으면 null
        return null;
    }

    // 토큰 생성
    public String createToken(String username, UserRoleEnum role) {
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(username) // 토큰 제목을 username으로 설정
                        .claim(AUTHORIZATION_KEY, role) // 토큰에 사용자 지정 클레임 추가 -> "auth" : ADMIN과 같이 페이로드에 추가됨
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME)) // 토큰 만료날짜 설정
                        .setIssuedAt(date) // 토큰 발급 시간 설정
                        .signWith(key, signatureAlgorithm) // 토큰 생성
                        .compact(); // JWT를 빌드하고, 이를 압축된 URL 문자열로 변환
    }

    // 토큰 검증
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch(SecurityException | MalformedJwtException e){ // Base64로 인코딩 되지 않았거나 JWT형식을 따르지 않는 토큰
            log.info("Invalid JWT signature, 유효하지 않은 JWT 서명입니다.");
        } catch(ExpiredJwtException e){
            log.info("Expired JWT token, 만료된 JWT token 입니다.");
        } catch(UnsupportedJwtException e){ // 예상하지 못한 형식의 JWT(헤더나 페이로드가 예상되지 않는 형식인 경우)
            log.info("Unsupported JWT token, 지원되지 않은 JWT 토큰 입니다.");
        } catch(IllegalArgumentException e){ // 토큰이 비었거나, JWT형식과 무관한 값인 경우
            log.info("JWT claims is empty, 잘못된 JWT 토큰입니다.");
        }
        return false;

    }

    // 토큰에서 사용자 정보 가져오기
    public Claims getUserInfoFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
