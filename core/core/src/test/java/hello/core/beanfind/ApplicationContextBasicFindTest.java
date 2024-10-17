package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    /*
    org.assertj.core.api.Assertions : assertThat,
    assertThat(타겟).메서드().메서드(): 메서드 체이닝이 가능하기에 junit이 아닌 assertj의 assertThat을 많이 사용한다.
    org.junit.jupiter.api.Assertions : assertThrows
    assertThrow(): 발생이 예상되는 예외의 타입, 예외가 발생될 수 있는 코드블록을 파라미터로 받아서 실행

    assertThat의 메서드: isEqualTo, isInstanceOf
    isInstanceOf(Class<?> type): 실제값이 기대값 타입의 인스턴스인지 검증

    */

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입만으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 구체타입으로 조회는 유연성이 떨어지기때문에 지양한다.
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
        assertThrows(NoSuchBeanDefinitionException.class, () ->
            ac.getBean("없는 빈 이름", MemberService.class));
    }
}
