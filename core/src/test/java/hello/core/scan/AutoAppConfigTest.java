package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {

    @Configuration
    @EnableJpaRepositories(basePackages = "hello.core")
    @EntityScan(basePackages = "hello.core")
    static class JpaConfig {
        @Bean
        public DataSource dataSource() {
            return new DriverManagerDataSource("jdbc:h2:mem:testdb", "sa", "");
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
            LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
            emf.setDataSource(dataSource);
            emf.setPackagesToScan("com.example.domain");
            emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

            Properties properties = new Properties();
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            emf.setJpaProperties(properties);

            return emf;
        }
        @Bean
        public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
            return new JpaTransactionManager(emf);
        }
    }

    @Test
    void basicScan(){
        /*
         EntityManager를 사용하려면 jpa관련해 설정하는 클래스 만들고, 클래스도 등록해야한다.
         @PersistenceContext를 쓰면 스프링 컨테이너에서 자동으로 생성해준다.
         하지만 AnnotationConfigApplicationContext에서 생성하는 스프링 컨테이너는 그러한 기능이 없어서 오류가 발생
         따라서 jpa관련 설정도 따로 해주고, AnnotationConfigApplicationContext를 생성 할 때에는 jpa클래스도 등록해줘야한다.
         그리고 JPA를 이용하기 위해서 DB 연결 설정도 따로 해주어야한다.
        */

        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, JpaConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

    }

}
