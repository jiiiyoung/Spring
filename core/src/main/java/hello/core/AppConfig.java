package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Config는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
// 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // DB를 바꾸고 싶으면 해당 Repository를 바꾸면 된다.
        return new MemoryMemberRepository();
    }
   return new FixDiscountPolicy();
}

