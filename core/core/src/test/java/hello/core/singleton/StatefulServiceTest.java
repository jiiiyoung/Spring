package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA : A ����� 10000�� �ֹ�
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB : B ����� 20000�� �ֹ�
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA : ����� A �ֹ��ݾ� ��ȸ
//        int price = statefulService1.getPrice();
        // ThreadA : ����� A�� 10000���� ��������� ���� �ٸ��� 20000�� ���
//        System.out.println("price = " + price);

        // ���� return price�� ���ٶ� �߻��ϴ� ��!
        System.out.println("price = " + userAPrice);

        // ��ü �� �ϳ��� �ν��Ͻ��� ������ �� �ֱ� ������ �Ͼ�� ��!
        // ���� ���� �ʵ�� ������(stateless)�� �����ϱ�
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
