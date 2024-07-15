package com.example.myselectshopbeta.myselectshopbeta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 최저가격 등록할 때 보낼 가격
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductMypriceRequestDto {
    private int myprice;
}
