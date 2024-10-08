package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Config는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
// 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.
@Configuration // 이 어노테이션이 없다면 싱글톤 보장 안됨
public class AppConfig {

    // Repository를 바꾸고 싶다면 AppConfig로 와서 바꾸면 된다.

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    // @Bean memberService -> repository -> new MemoryMemberRepository()
    // @Bean orderService -> repository -> new MemoryMemberRepository()
    // MemoryMemberRepository를 두 개 생성? 싱글톤에 어긋나나?



    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        // memberService에서 호출되면 한번 호출 되고, 그 후에는 ~~~~~~~~~해서 싱글톤을 보장한다.

        // DB를 바꾸고 싶으면 해당 Repository를 바꾸면 된다.
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        // 할인 정책을 바꾸고 싶다면 이곳만 바꾸면 된다.
        return new RateDiscountPolicy();
//        return new FixDiscountPolicy();
    }

}

