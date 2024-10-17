package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    /*
    ApplicationContext.getBeanDefinitionNames(): �������� ��ϵ� ��� �� �̸��� ��ȸ�Ѵ�.
    ApplicationContext.getBean(���̸�): �� �̸����� �� ��ü(�ν��Ͻ�)�� ��ȸ�Ѵ�.
                      .getBean(���̸�, Ÿ��)
                      .getBean(Ÿ��)
    AnnotationConfigApplicationContext.getBeanDefinition(): Bean�� ���� meta data �������� ��ȯ�Ѵ�.
    */


    @Test
    @DisplayName("��� �� ����ϱ�")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for(String beanDefinitionName : beanDefinitionNames){
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name=" + beanDefinitionName + ", object=" + bean);
        }
    }

    @Test
    @DisplayName("���ø����̼� �� ����ϱ�")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);


            // ROLE_APPLICATION: ���� ����� ���ø����̼� ��
            // ROLE_INFRASTRUCTURE: �������� ���ο��� ����ϴ� ��
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION ){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name=" + beanDefinitionName + ", object=" + bean);
            }
        }

    }
}
