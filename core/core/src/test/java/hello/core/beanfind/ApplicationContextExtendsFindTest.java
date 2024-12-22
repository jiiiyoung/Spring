package hello.core.beanfind;

import hello.core.order.discount.DiscountPolicy;
import hello.core.order.discount.FixDiscountPolicy;
import hello.core.order.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("�θ� Ÿ������ ��ȸ��, �ڽ��� �� �̻� ������, �ߺ� ������ �߻��Ѵ�.")
    void findBeanByParentTypeDuplicate(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> {
            ac.getBean(DiscountPolicy.class);
        });
    }

    @Test
    @DisplayName("�θ� Ÿ������ ��ȸ��, �ڽ��� �� �̻� ������, �� �̸��� �����ϸ� �ȴ�.")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("Ư�� ���� Ÿ������ ��ȸ")
    void findBeanBySubType() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("�θ� Ÿ������ ��� ��ȸ�ϱ�")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);

        assertThat(beansOfType.size()).isEqualTo(2);

        for(String key : beansOfType.keySet()){
            System.out.println("key = " + key + " value = "+ beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("�θ� Ÿ������ ��� ��ȸ�ϱ� - Object")
    void findAllBeanByObjectType() {
        // ��� �ڹ� ��ü�� �ְ� �θ��� Object�� ��ȸ�ϸ� ��� ���������� ��ȸ�� �� �ִ�.
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);

        for(String key : beansOfType.keySet()){
            System.out.println("key = " + key + " value = "+ beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }


}
