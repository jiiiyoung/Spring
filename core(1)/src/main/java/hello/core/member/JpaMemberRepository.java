package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

// 현재 MeberRepository와 bean등록이 겹쳐 오류 발생. 일단 repository 주석처리
//@Repository
public class JpaMemberRepository implements MemberRepository {

    @PersistenceContext // 스프링에서 만들어둔 EntityManager를 자동으로 주입받을때 사용
    private EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Member findById(Long memberId) {
        return em.find(Member.class, memberId);
    }
}
