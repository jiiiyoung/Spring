package com.example.springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


// HTTP 요청 본문에 담겨오는 데이터를 자바 객체로 변환하는 역할
// ex) (이런 정보를 가진) 관심 상품을 등록할거야! -> 관심상품의 정보가 requestDto에 담겨서 온다.0
// 새로운 엔티티 생성 or 기존 엔티티 업데이트
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    // 관심 상품 명
    private String title;

    // 관심 상품 썸네일
    private String image;

    // 관심 상품 구매 링크 URL
    private String link;

    // 관심 상품의 최저가
    private int lprice;

}

