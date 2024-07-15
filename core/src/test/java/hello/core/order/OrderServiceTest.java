package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {

        // given -> 주문을 할 사람이 필요,
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // when -> 주문을 한다.
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // then -> 할인이 되는지 검증하기
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}

