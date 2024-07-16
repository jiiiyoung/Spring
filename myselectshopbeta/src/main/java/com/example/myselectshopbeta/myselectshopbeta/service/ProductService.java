package com.example.myselectshopbeta.myselectshopbeta.service;

import com.example.myselectshopbeta.myselectshopbeta.dto.ProductMypriceRequestDto;
import com.example.myselectshopbeta.myselectshopbeta.dto.ProductRequestDto;
import com.example.myselectshopbeta.myselectshopbeta.dto.ProductResponseDto;
import com.example.myselectshopbeta.myselectshopbeta.entity.Product;
import com.example.myselectshopbeta.myselectshopbeta.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class ProductService {

    // 멤버 변수 선언
    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    // createProduct, getProducts, updateProduct(id, requestDto)
    public ProductResponseDto createProduct(ProductRequestDto requestDto) throws SQLException {
        // 요청받은 DTO로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);

        return productRepository.createProduct(product);

    }

    public List<ProductResponseDto> getProducts() throws SQLException{
        // Appconfig에 생성자 만들기, Service에도 생성자만들기
        // ProductRepository productRepository = new ProductRepository();

        return productRepository.getProducts();
    }

    public Long updateProduct(Long id, ProductMypriceRequestDto requestDto) throws SQLException{
        // Appconfig에 생성자 만들기, Service에도 생성자만들기
//        ProductRepository productRepository = new ProductRepository();
        Product product = productRepository.getProduct(id);

        if(product == null){
            throw new NullPointerException("해당 상품은 존재하지 않습니다.");
        }

        return productRepository.updateProduct(product.getId(), requestDto);

    }

}
