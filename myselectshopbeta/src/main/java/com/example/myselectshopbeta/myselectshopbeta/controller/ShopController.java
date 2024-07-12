package com.example.myselectshopbeta.myselectshopbeta.controller;


import com.example.myselectshopbeta.myselectshopbeta.dto.ProductRequestDto;
import com.example.myselectshopbeta.myselectshopbeta.dto.ProductResponseDto;
import com.example.myselectshopbeta.myselectshopbeta.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.sql.*;

@Controller
@RequestMapping("/api")
public class ShopController {

    // 홈화면
    @GetMapping("/shop")
    public ModelAndView shop() {
        return new ModelAndView("index");
    }

    // 관심상품 등록
    @PostMapping("/products")
    public ProductResponseDto add(@RequestBody ProductRequestDto requestDto) throws SQLException {
        // Dto에 저장할 객체 만들기
        Product product = new Product(requestDto);

        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:db", "sa", "");

        // DB Query 작성
        PreparedStatement ps = connection.prepareStatement("select max(id) as id from product");
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            // product id 설정 = product 테이블의 마지막 id + 1
            product.setId(rs.getLong("id") + 1);
        }else{
            throw new SQLException("product 테이블의 마지막 id 값을 찾아오지 못했습니다.");
        }

        ps = connection.prepareStatement("insert into product(id, title, image, link, lprice, myprice) values(?,?,?,?, ?, ?)");

        ps.setLong(1, product.getId());
        ps.setString(2, product.getTitle());
        ps.setString(3, product.getImage());
        ps.setString(4, product.getLink());
        ps.setInt(5, product.getLprice());
        ps.setInt(6, product.getMyprice());

        // DB Query 실행
        ps.executeUpdate();

        // DB 연결 해제
        ps.close();
        connection.close();

        // 응답 보내기
        return new ProductResponseDto(product);
    }

    // 관심상품 조회
    @GetMapping("/products")
    public Dto findAll(){

    }

    // 최저가격 등록
    @PutMapping("/products/{id}")
    public Long update(@PathVariable int id){

    }
}

