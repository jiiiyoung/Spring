package com.example.logintest.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 생성자를 통해 값 변경 목적으로 접근하지 못하도록 차단
@Entity
@Builder
@AllArgsConstructor // 이건 제한 안함. 왜냐면 생성자 만들어서 넣어야하기 때문에
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "used_id")
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private String imageUrl;
    private int age;
    private String city;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String socialId;

    private String refreshToken;

    public void authorizeUser(){
        this.role = Role.USER;
    }

    public void passwordEncode(PasswordEncoder encoder){
        this.password = encoder.encode(this.password);
    }

    public void updateRefreshToken(String updateRefreshToken){
        this.refreshToken = updateRefreshToken;
    }

}
