package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    // 회원 조회, 할인정책여부 결정
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

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
}
