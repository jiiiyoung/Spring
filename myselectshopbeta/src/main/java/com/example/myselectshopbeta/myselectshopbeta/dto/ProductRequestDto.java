package com.example.myselectshopbeta.myselectshopbeta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.thymeleaf.spring6.processor.SpringActionTagProcessor;


// 관심 상품을 조회할 때 DB의 정보를 팢아서 보내줘야함
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

