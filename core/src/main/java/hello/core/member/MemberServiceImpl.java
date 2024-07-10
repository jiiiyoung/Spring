package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // MemberServiceImpl은 MemberRepository인 인터페이스(추상화)도 의존하고, MemoryMemberRepository 클래스인 구현체에도 의존한다.
    // 구현체에 의존하기 않기 위해 인터페이스를 이용했는데 맞지 않다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        // 다형성에 의해 MemberRepository가 아닌 오버라이드한 MemoryMemberRepository의 save가 호출된다.
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
