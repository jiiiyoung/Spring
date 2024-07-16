package com.example.myselectshopbeta.myselectshopbeta.dto;

import com.example.myselectshopbeta.myselectshopbeta.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Product 테이블에서 받은 정보를 객체로 변환하는 것

@Getter
@NoArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String title;
    private String link;
    private String image;
    private int lprice;
    private int myprice;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.link = product.getLink();
        this.image = product.getImage();
        this.lprice = product.getLowprice();
        this.myprice = product.getMyprice();
    }
}
