package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {

    @Test
    void basicScan(){
        /*
         EntityManager를 사용하려면 jpa관련해 설정하는 클래스 만들고, 클래스도 등록해야한다.
         @PersistenceContext를 쓰면 스프링 컨테이너에서 자동으로 생성해준다.
         하지만 AnnotationConfigApplicationContext해서 생성하는 스프링 컨테이너는 그러한 기능이 없어서 오류가 발생
         따라서 jpa관련 설정도 따로 해주고, AnnotationConfigApplicationContext를 생성 할 때에는 jpa클래스도 등록해줘야한다.
        */

        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

    }
}
