package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        // memberService는 호출할때마다 객체가 생성되어 요청이 올때마다 JVM 메모리에 객체가 쌓이게 되기때문에 메모리 낭비가 심하다
        // 이런 경우 객체가 딱 하나만 생성되고 공유되도록 설계하면 된다.( 이것이 싱글톤 패턴 )

        // 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        /*
        [출력결과]
        memberService1 = hello.core.member.service.MemberServiceImpl@589b3632
        memberService2 = hello.core.member.service.MemberServiceImpl@45f45fa1
        */

        // memberService1 != memberService2
        assertThat(memberService1).isNotEqualTo(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        // 싱글톤 서비스에서 클래스가 로드 될 때 객체가 생성되어 저장되어 있다. 그 객체를 getInstance를 가져와서 불러오기
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // 싱글톤 서비스가 같은 객체를 사용하는지 확인
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        /*
        [출력결과]
        singletonService1 = hello.core.singleton.SingletonService@4b0b0854
        singletonService2 = hello.core.singleton.SingletonService@4b0b0854
        */

        // isSameAs: ==와 같은 의미로 동일한 메모리 주소를 참조하는지 비교
        // isEqualTo: equals와 같은 의미로 두 객체의 내용이 동일한지 확인
        assertThat(singletonService1).isSameAs(singletonService2);


        /* 싱글톤 패턴 문제점
        - 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
        - 의존관계상 클라이언트가 구체 클레스에 의존한다 -> DIP를 위반한다.
        - 쿨라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
        - 테스트하기 어렵다.
        - 내부 속성을 변경하거나 초기화 하기 어렵다.
        - private 생성자로 자식 클래스를 만들기 어렵다.
        - 결론적으로 유연성이 떨어진다.
        - 안티패턴으로 불리기도 한다.
        */


    }
    // 스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하면서 객체 인스턴스를 싱글톤으로 관리한다.
    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        //AppConfig appConfig = new AppConfig(); 대신 @Configuration 주석이 달린 클래스 설정을 이용해 Bean을 자동으로 관리
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회: 호출할 때 마다 같은 객체를 반환
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 2. 조회: 호출할 때 마다 같은 객체를 반환
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조 값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
