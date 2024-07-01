package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcMemberRepository implements MemberRepository {

    private final DataSource dataSource;

    public JdbcMemberRepository(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member save(final Member member) {
        String sql = "insert into member (name) values (?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = pstmt.getConnection();
            pstmt = conn.prepareStatement();
        }

    }
}
