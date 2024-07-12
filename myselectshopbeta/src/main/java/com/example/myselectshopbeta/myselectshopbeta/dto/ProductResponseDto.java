package com.example.myselectshopbeta.myselectshopbeta.dto;

import com.example.myselectshopbeta.myselectshopbeta.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 관심 상품을 등록할 때 받은 정보
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
        this.lprice = product.getLprice();
        this.myprice = product.getMyprice();
    }
}
