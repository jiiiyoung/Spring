package com.example.myselectshopbeta.myselectshopbeta.repository;

import com.example.myselectshopbeta.myselectshopbeta.dto.ProductMypriceRequestDto;
import com.example.myselectshopbeta.myselectshopbeta.dto.ProductResponseDto;
import com.example.myselectshopbeta.myselectshopbeta.entity.Product;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@Component
public class ProductRepository_beforeRefactoring {

    private final String dbUrl;
    private final String username;
    private final String password;

    public ProductRepository_beforeRefactoring(String dbUrl, String username, String password) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
    }

    // createProduct, getProducts, updateProduct, getProduct(id)

    public ProductResponseDto createProduct(Product product) throws SQLException {

        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:db", "sa", "");

        // DB Query 작성
        PreparedStatement ps = connection.prepareStatement("select max(id) as id from product");
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            product.setId(rs.getLong("id") + 1);
        } else {
            throw new SQLException("product 테이블의 마지막 id 값을 찾아오지 못했습니다.");
        }

        ps = connection.prepareStatement("insert into product(id, title, image, link, lprice, myprice) values(?, ?, ?, ?, ?, ?)");
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

        return new ProductResponseDto(product);
    }

    public List<ProductResponseDto> getProducts() throws SQLException{
        // 모든 products 조회해서 저장하기
        List<ProductResponseDto> products = new ArrayList<>();

        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:db", "sa", "");

        // DB Query 작성
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from product");

        if(rs.next()){
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setTitle(rs.getString("title"));
            product.setImage(rs.getString("image"));
            product.setLink(rs.getString("link"));
            product.setLowprice(rs.getInt("lowprice"));
            product.setMyprice(rs.getInt("myprice"));
            products.add(new ProductResponseDto(product));
        }
        // DB 연결 해제
        rs.close();
        connection.close();

        return products;
    }

    public Long updateProduct(Long id, ProductMypriceRequestDto requestDto) throws SQLException {
        Product product = new Product();

        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:db", "sa", "");

        // DB Query 작성
        PreparedStatement ps = connection.prepareStatement("update product myprice = ? where id = ?");
        ps.setInt(1, requestDto.getMyprice());
        ps.setLong(2, id);

        // DB Query 실행
        ps.executeQuery();

        // DB 연결 해제
        ps.close();
        connection.close();

        // 왜 null값을 보내는 걸까?
        return null;
    }

    public Product getProduct(Long id) throws SQLException {
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
            product.setTitle(rs.getString("title"));
            product.setImage(rs.getString("image"));
            product.setLink(rs.getString("link"));
            product.setLowprice(rs.getInt("lowprice"));
            product.setMyprice(rs.getInt("myprice"));
        }

        // DB 연결 해제
        rs.close();
        ps.close();
        connection.close();

        return product;

    }


}
