package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // MemberServiceImpl은 MemberRepository인 인터페이스(추상화)도 의존하고, MemoryMemberRepository 클래스인 구현체에도 의존한다.
    // 구현체에 의존하기 않기 위해 인터페이스를 이용했는데 맞지 않다.

    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 생성자주입
    private final MemberRepository memberRepository;

    // 컴포넌트 스캔을 쓰게 되면, 의존관계를 수동으로 설정할 공간이 없어서 @Autowired를 쓰게 되는 것이다.
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        // 다형성에 의해 MemberRepository가 아닌 오버라이드한 MemoryMemberRepository의 save가 호출된다.
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}