package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.entity.Grade;
import hello.core.member.entity.Member;
import hello.core.member.entity.Order;
import hello.core.member.service.MemberService;
import hello.core.order.service.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {

        //given
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // when
        Order order = orderService.createOrder(memberId, "itemA", 20000);

        // then : vip의 경우는 1000원 할인 아니면 0원 할인
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }



}
