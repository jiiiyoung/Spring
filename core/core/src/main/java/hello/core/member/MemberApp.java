package hello.core.member;


import hello.core.AppConfig;
import hello.core.member.entity.Grade;
import hello.core.member.entity.Member;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


// 애플리케이션 상으로 테스트하기 -> 테스트 코드 작성하기로 변경!
public class MemberApp {
    public static void main(String[] args) {
/*
        // AppConfig가 Configuration어노테이션으로 되어 있기 때문에 스프링컨테이너를 이용한다.
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
*/

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member: " + member.getName());
        System.out.println("find Member: " + findMember.getName());
    }
}
