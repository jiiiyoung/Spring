package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    // 회원 조회
    // 회원 조회를 위해 DB를 바꾸려면 클라이언트 코드를 변경해야 한다. 따라서 OCP, DIP를 위반한다.
//     private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 할인 정책여부 결정
    // 할인 정책을 바꾸려먼 Impl(클라이언트) 코드를 변경해야 한다. 따라서 OCP, DIP를 위반
//     private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // OCP, DIP원칙을 지키기 위해 인터페이스에만 의존하도록 코드 변경.
    // 의존관계 주입(DI) : 외부에서 의존관계를 주입해 준다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 회원 정보 조회
        Member member = memberRepository.findById(memberId);

        // 할인 정책 여부 결정
        // 회원정보를 넘겨서 등급을 확인해 할인정책을 결정할지
        // 등급만 넘겨 할인정책을 결정할지는 개인의 선택
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 주문을 생성하여 넘기기
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
