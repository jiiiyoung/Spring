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
    @DisplayName("������ ���� ������ DI �����̳�")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        // memberService�� ȣ���Ҷ����� ��ü�� �����Ǿ� ��û�� �ö����� JVM �޸𸮿� ��ü�� ���̰� �Ǳ⶧���� �޸� ���� ���ϴ�
        // �̷� ��� ��ü�� �� �ϳ��� �����ǰ� �����ǵ��� �����ϸ� �ȴ�.( �̰��� �̱��� ���� )

        // 1. ��ȸ : ȣ���� �� ���� ��ü�� ����
        MemberService memberService1 = appConfig.memberService();

        // 2. ��ȸ : ȣ���� �� ���� ��ü�� ����
        MemberService memberService2 = appConfig.memberService();

        // �������� �ٸ� ���� Ȯ��
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        /*
        [��°��]
        memberService1 = hello.core.member.service.MemberServiceImpl@589b3632
        memberService2 = hello.core.member.service.MemberServiceImpl@45f45fa1
        */

        // memberService1 != memberService2
        assertThat(memberService1).isNotEqualTo(memberService2);
    }

    @Test
    @DisplayName("�̱��� ������ ������ ��ü ���")
    void singletonServiceTest(){
        // �̱��� ���񽺿��� Ŭ������ �ε� �� �� ��ü�� �����Ǿ� ����Ǿ� �ִ�. �� ��ü�� getInstance�� �����ͼ� �ҷ�����
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // �̱��� ���񽺰� ���� ��ü�� ����ϴ��� Ȯ��
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        /*
        [��°��]
        singletonService1 = hello.core.singleton.SingletonService@4b0b0854
        singletonService2 = hello.core.singleton.SingletonService@4b0b0854
        */

        // isSameAs: ==�� ���� �ǹ̷� ������ �޸� �ּҸ� �����ϴ��� ��
        // isEqualTo: equals�� ���� �ǹ̷� �� ��ü�� ������ �������� Ȯ��
        assertThat(singletonService1).isSameAs(singletonService2);


        /* �̱��� ���� ������
        - �̱��� ������ �����ϴ� �ڵ� ��ü�� ���� ����.
        - ��������� Ŭ���̾�Ʈ�� ��ü Ŭ������ �����Ѵ� -> DIP�� �����Ѵ�.
        - ����̾�Ʈ�� ��ü Ŭ������ �����ؼ� OCP ��Ģ�� ������ ���ɼ��� ����.
        - �׽�Ʈ�ϱ� ��ƴ�.
        - ���� �Ӽ��� �����ϰų� �ʱ�ȭ �ϱ� ��ƴ�.
        - private �����ڷ� �ڽ� Ŭ������ ����� ��ƴ�.
        - ��������� �������� ��������.
        - ��Ƽ�������� �Ҹ��⵵ �Ѵ�.
        */


    }
    // ������ �����̳ʴ� �̱��� ������ �������� �ذ��ϸ鼭 ��ü �ν��Ͻ��� �̱������� �����Ѵ�.
    @Test
    @DisplayName("������ �����̳ʿ� �̱���")
    void springContainer() {

        //AppConfig appConfig = new AppConfig(); ��� @Configuration �ּ��� �޸� Ŭ���� ������ �̿��� Bean�� �ڵ����� ����
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. ��ȸ: ȣ���� �� ���� ���� ��ü�� ��ȯ
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 2. ��ȸ: ȣ���� �� ���� ���� ��ü�� ��ȯ
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // ���� ���� ���� ���� Ȯ��
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
