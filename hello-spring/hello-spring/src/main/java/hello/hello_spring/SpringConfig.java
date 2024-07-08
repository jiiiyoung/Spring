package hello.hello_spring;


import hello.hello_spring.aop.TimeTraceAop;
import hello.hello_spring.repository.JpaMemberRepository;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private final DataSource dataSource;
//    private final EntityManager em;
    private final MemberRepository memberRepository;


//    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){
//        return new JpaMemberRepository(em);
//    }

    // AOP에서 component로 등록할 수 있지만 스프링빈에 등록해두고 AOP에 있다는 것을 인지하기 쉽게 하기 위해 주로 스프링빈에 등록해서 사용
    // 하지만 해당 코드를 사용하면 순환참조가 발생함
    /*
    * 순환 참조 발생 이유
     AOP에서 hello.hello_spring 하위 파일들에 모두 AOP를 적용시켰다.
     그 하위 파일에는 SpringConfig.java도 포함되어 있고, 이 코드가 AOP를 생성하는(Bean에 등록하는) 코드이기 때문에 순환참조가 발생한다.
     따라서 이를 해결하기 위해서는
     1. AOP에서 @Around에서 SpringConfig파일을 제외시키기 -> 해봤는데 안됨,,, 왜지?
     2. 직접 Bean을 등록하는 것이 아닌 @Component로 등록하기.
     3. application.properties에 `spring.main.allow-bean-definition-overriding=true` 추가하기.
    */
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return timeTraceAop();
//    }
}
