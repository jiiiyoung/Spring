package hello.core.order.discount;

import hello.core.member.entity.Grade;
import hello.core.member.entity.Member;

public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {

        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100; // 할인해줘야할 금액
        }else{
            return 0;
        }

    }
}
