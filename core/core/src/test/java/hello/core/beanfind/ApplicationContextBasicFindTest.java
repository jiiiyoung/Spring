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
    assertThat(Ÿ��).�޼���().�޼���(): �޼��� ü�̴��� �����ϱ⿡ junit�� �ƴ� assertj�� assertThat�� ���� ����Ѵ�.
    org.junit.jupiter.api.Assertions : assertThrows
    assertThrow(): �߻��� ����Ǵ� ������ Ÿ��, ���ܰ� �߻��� �� �ִ� �ڵ����� �Ķ���ͷ� �޾Ƽ� ����

    assertThat�� �޼���: isEqualTo, isInstanceOf
    isInstanceOf(Class<?> type): �������� ��밪 Ÿ���� �ν��Ͻ����� ����

    */

    @Test
    @DisplayName("�� �̸����� ��ȸ")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("�̸� ���� Ÿ�Ը����� ��ȸ")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // ��üŸ������ ��ȸ�� �������� �������⶧���� �����Ѵ�.
    @Test
    @DisplayName("��ü Ÿ������ ��ȸ")
    void findBeanByName2(){
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("�� �̸����� ��ȸX")
    void findBeanByNameX(){
        assertThrows(NoSuchBeanDefinitionException.class, () ->
            ac.getBean("���� �� �̸�", MemberService.class));
    }
}
