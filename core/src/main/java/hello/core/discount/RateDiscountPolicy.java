package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10; // 10% 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            // 할인되는 금액만 반환(원래 금액에서 빼야하는 금액)
            return price * discountPercent / 100;
        } else{
            return 0;
        }
    }
}
