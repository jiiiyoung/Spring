package hello.core.order.service;

import hello.core.member.entity.Member;
import hello.core.member.entity.Order;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;
import hello.core.order.discount.DiscountPolicy;
import hello.core.order.discount.FixDiscountPolicy;

public class OrderServiceImpl implements OrderService {

/*
    // 이렇게 하면, 관심사의 분리가 되지 않아 OCP를 위반한다.
    // 따라서 private로 해주고, 생성자를 생성해준 후, webConfig에서 등록해준다.
    // 또한, 인터페이스에 의존하는 것이 아닌 구현체에 의존한다. 따라서 이러한 부분을 개선해야 한다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
*/


    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
