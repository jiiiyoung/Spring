package hello.core.discount;

import hello.core.member.entity.Grade;
import hello.core.member.entity.Member;
import hello.core.order.discount.RateDiscountPolicy;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP�� 10% ������ ����Ǿ�� �Ѵ�.")
    void vip_O(){
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        // when
        int discount = rateDiscountPolicy.discount(member, 10000);

        // then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP�� �ƴϸ� ������ ������� �ʾƾ� �Ѵ�.")
    void vip_X(){
        // given
        Member member = new Member(1L, "memberBASIC", Grade.BASIC);

        // when
        int discount = rateDiscountPolicy.discount(member, 10000);

        // then
        assertThat(discount).isEqualTo(0);
    }
    

}
