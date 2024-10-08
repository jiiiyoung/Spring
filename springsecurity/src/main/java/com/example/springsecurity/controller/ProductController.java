package com.example.springsecurity.controller;


import com.example.myselectshopbeta.myselectshopbeta.dto.ProductMypriceRequestDto;
import com.example.myselectshopbeta.myselectshopbeta.dto.ProductRequestDto;
import com.example.myselectshopbeta.myselectshopbeta.dto.ProductResponseDto;
import com.example.myselectshopbeta.myselectshopbeta.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    // Lombok의 @RequiredArgsConstructor을 사용하면 아래 코드 생략 가능
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }


    // 관심상품 등록
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto, HttpServletRequest request) {

        // 응답보내기
        return productService.createProduct(requestDto, request);

        /*  컨트롤러가 모든 역할을 다 할 때 cotroller, service, repository의 코드가 다 있다.
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

        ps = connection.prepareStatement("insert into product(id, title, image, link, lowprice, myprice) values(?,?,?,?, ?, ?)");

        ps.setLong(1, product.getId());
        ps.setString(2, product.getTitle());
        ps.setString(3, product.getImage());
        ps.setString(4, product.getLink());
        ps.setInt(5, product.getLowprice());
        ps.setInt(6, product.getMyprice());

        // DB Query 실행
        ps.executeUpdate();

        // DB 연결 해제
        ps.close();
        connection.close();

        // 응답 보내기
        return new ProductResponseDto(product);
        */

    }

    // 관심상품(전체) 조회
    @Secured("ROLE_ADMIN") // 과리자용 등록된 모든 상품 목록 조회
    @GetMapping("/products")
    public List<ProductResponseDto> getProducts(HttpServletRequest request){

        // 응답 보내기
        return productService.getProducts(request);

        /*
        // DB의 데이터 가져와 담을 리스트 생성(원래 있던 리스트를 초기화)
        List<ProductResponseDto> products = new ArrayList<>();

        // DB 연결하기
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:db", "sa", "");

        // DB Qeury 작성 및 실행
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from product");

        // DB Query 결과를 상품 객체 리스트로 변환
        while(rs.next()){ // 한줄씩 확인하면서 products에 추가.
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setImage(rs.getString("image"));
            product.setLink(rs.getString("link"));
            product.setLowprice(rs.getInt("lowprice"));
            product.setMyprice(rs.getInt("myprice"));
            product.setTitle(rs.getString(("title")));
            products.add(new ProductResponseDto(product));
        }

        // DB 연결 해제
        rs.close();
        connection.close();

        // 응답 보내기
        return products;
        */
    }

    // 최저가격 등록
    @PutMapping("/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto, HttpServletRequest request) {

        // 응답 보내기(업데이트 된 상품 id)
        return productService.updateProduct(id, requestDto, request);

        /*
        Product product = new Product();

        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:db", "sa", "");

        // DB Query 작성
        PreparedStatement ps = connection.prepareStatement("select * from product where id = ?");
        ps.setLong(1, id);

        // DB Query 실행
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            product.setId(rs.getLong("id"));
            product.setImage(rs.getString("title"));
            product.setLink(rs.getString("link"));
            product.setLowprice(rs.getInt("lowprice"));
            product.setMyprice(rs.getInt("myprice"));
            product.setTitle(rs.getString("title"));
        } else {
            throw new NullPointerException("해당 아이디가 존재하지 않습니다.");
        }

        // DB Query 작성
        ps = connection.prepareStatement("update product set myprice = ? where id = ?");
        ps.setInt(1, requestDto.getMyprice());
        ps.setLong(2, product.getId());

        // DB Query 실행
        ps.executeUpdate();

        // DB 연결 해제
        rs.close();
        ps.close();
        connection.close();

        // 응답 보내기(업데이트 된 상품 id)
        return product.getId();
        */

    }
}

