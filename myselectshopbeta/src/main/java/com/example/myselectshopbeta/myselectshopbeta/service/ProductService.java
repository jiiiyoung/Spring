package com.example.myselectshopbeta.myselectshopbeta.service;

import com.example.myselectshopbeta.myselectshopbeta.dto.ProductMypriceRequestDto;
import com.example.myselectshopbeta.myselectshopbeta.dto.ProductRequestDto;
import com.example.myselectshopbeta.myselectshopbeta.dto.ProductResponseDto;
import com.example.myselectshopbeta.myselectshopbeta.entity.Product;

import com.example.myselectshopbeta.myselectshopbeta.entity.User;
import com.example.myselectshopbeta.myselectshopbeta.entity.UserRoleEnum;
import com.example.myselectshopbeta.myselectshopbeta.jwt.JwtUtil;
import com.example.myselectshopbeta.myselectshopbeta.repository.ProductRepository;
import com.example.myselectshopbeta.myselectshopbeta.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    // 멤버 변수 선언
    private final ProductRepository productRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    /*
    @Autowired
    public ProductService(ProductRepository_beforeRefactoring productRepository) {
        this.productRepository = productRepository;
    }
    */

    /*
    // 스프링 컨테이너에서 빈을 수동으로 가져오는 방법
    @Autowired
    public ProductService(ApplicationContext context){
        // 1. '빈' 이름으로 가져오기
        ProductRepository productRepository = (ProductRepository) context.getBean("productRepository");
        // 2. 'Bean' 클래스 형시긍로 가져오기
        ProductRepository productRepository = context.getBean(ProductRepository.class);
        this.productRepository = productRepository;
    }
    */


    @Transactional
    // createProduct, getProducts, updateProduct(id, requestDto)
    public ProductResponseDto createProduct(ProductRequestDto requestDto, HttpServletRequest request) {
        // request에서 토큰 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자 정보가 존재하지 않습니다.")
            );

            // 요청받은 DTO로 DB에 저장할 객체 만들기
            Product product = productRepository.saveAndFlush(new Product(requestDto, user.getId()));

            return new ProductResponseDto(product);
        } else {
            return null;

        }
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getProducts(HttpServletRequest request){
        // Appconfig에 생성자 만들기, Service에도 생성자만들기
        // ProductRepository productRepository = new ProductRepository();

        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 조회 가능
        if(token != null) {
            // 토큰 검증
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용해 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자 정보가 존재하지 않습니다.")
            );

            // 사용자 권한 가져와서 ADMIN이면 전체조회, USER면 본인이 추가한 부분 조회
            UserRoleEnum userRoleEnum = user.getRole();
            System.out.println("role = " + userRoleEnum);

            List<ProductResponseDto> products = new ArrayList<>();

            List<Product> productList;
            if (userRoleEnum == UserRoleEnum.USER) {
                productList = productRepository.findAllByUserId(user.getId());
            } else {
                productList = productRepository.findAll();
            }
            for (Product product : productList) {
                products.add(new ProductResponseDto(product));
            }

            return products;
        } else{
            return null;
        }
    }

    @Transactional
    public Long updateProduct(Long id, ProductMypriceRequestDto requestDto, HttpServletRequest request){
        // Appconfig에 생성자 만들기, Service에도 생성자만들기
//        ProductRepository productRepository = new ProductRepository();

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            }else{
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB에서 검색
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
            );

            Product product = productRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("해당 상품은 존재하지 않습니다.")
            );

            product.update(requestDto);

            return product.getId();
        }else{
            return null;
        }
    }

}
