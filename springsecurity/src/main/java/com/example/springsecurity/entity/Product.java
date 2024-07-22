package com.example.springsecurity.entity;

import com.example.myselectshopbeta.myselectshopbeta.dto.ProductMypriceRequestDto;
import com.example.myselectshopbeta.myselectshopbeta.dto.ProductRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity // DB의 케이블 역할
@NoArgsConstructor
public class Product extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID가 자동으로 생성 및 증가
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lowprice;

    @Column(nullable = false)
    private int myprice;

    @Column(nullable = false)
    private Long userId;


    public Product(ProductRequestDto requestDto, Long userId){
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lowprice = requestDto.getLprice();
        this.myprice = 0;
        this.userId = userId;
    }

    public void update(ProductMypriceRequestDto requestDto){
        this.myprice = requestDto.getMyprice();
    }
}
