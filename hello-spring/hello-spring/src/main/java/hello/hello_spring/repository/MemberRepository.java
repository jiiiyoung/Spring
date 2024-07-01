package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 저장하기
    Optional<Member> findById(Long id); // id로 멤버 찾기
    Optional<Member> findByName(String name); // 이름으로 멤버 찾기
    List<Member> findAll();

}
