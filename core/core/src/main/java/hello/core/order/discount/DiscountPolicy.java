package hello.core.order.discount;

import hello.core.member.entity.Member;

// 할인정책
public interface DiscountPolicy {
    /**
    * @return 할인 대상 금액
    * */
    int discount(Member member, int price);
}
