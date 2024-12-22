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

        // ThreadA : A 사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB : B 사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA : 사용자 A 주문금액 조회
//        int price = statefulService1.getPrice();
        // ThreadA : 사용자 A는 10000원을 기대했지만 기대와 다르게 20000원 출력
//        System.out.println("price = " + price);

        // 만약 return price를 해줄때 발생하는 일!
        System.out.println("price = " + userAPrice);

        // 객체 당 하나의 인스턴스만 생성할 수 있기 때문에 일어나는 일!
        // 따라서 공유 필드는 무상태(stateless)로 설정하기
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
