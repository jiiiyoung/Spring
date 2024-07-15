package hello.core.order;

import hello.core.member.Member;

public interface OrderService {
    // 주문 생성 -> 주문을 반환
    Order createOrder(Long memberId, String itemName, int itemPrice);


}
