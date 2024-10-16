package hello.core.order.service;

import hello.core.member.entity.Member;
import hello.core.member.entity.Order;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;
import hello.core.order.discount.DiscountPolicy;
import hello.core.order.discount.FixDiscountPolicy;

public class OrderServiceImpl implements OrderService {

/*
    // �̷��� �ϸ�, ���ɻ��� �и��� ���� �ʾ� OCP�� �����Ѵ�.
    // ���� private�� ���ְ�, �����ڸ� �������� ��, webConfig���� ������ش�.
    // ����, �������̽��� �����ϴ� ���� �ƴ� ����ü�� �����Ѵ�. ���� �̷��� �κ��� �����ؾ� �Ѵ�.
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
