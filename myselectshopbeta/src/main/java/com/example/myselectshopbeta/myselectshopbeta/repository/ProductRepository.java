package com.example.myselectshopbeta.myselectshopbeta.repository;

import com.example.myselectshopbeta.myselectshopbeta.dto.ProductResponseDto;
import com.example.myselectshopbeta.myselectshopbeta.entity.Product;

import java.sql.*;

public class ProductRepository {
    // createProduct, getProducts, updateProduct

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
        ps.setInt(5, product.getLprice());
        ps.setInt(6, product.getMyprice());

        // DB Query 실행
        ps.executeUpdate();

        // DB 연결 해제
        ps.close();
        connection.close();

        return new ProductResponseDto(product);
    }

    public List<ProductRe>
}
