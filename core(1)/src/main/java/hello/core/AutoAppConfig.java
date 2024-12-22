package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// ComponentScan을 붙이면 Configuration이 붙은 설정정보들이 자동으로 등록이 된다.
@Configuration
@ComponentScan( // 컴포넌트 스캔을 지정하지 않으면 이 애너테이션이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다. 현재 패키지 위치 : hello.core
        // basePackages = "hello.core.member", // 컴포넌트 스캔을 다 할 필요가 없다면, 필요한 위치부터 탐색하도록 정하기, 배열{}로 여러 위치를 지정해도 된다.
        // basicPackageClasses = AutoAppConfig.class  // 지정한 클래스 패키지를 탐색위치로 지정한다.
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)// 해당 정보는 앞서 지정된 설정이 있어서 임시로 제거한 것.
        // Configuration이라는 어노테이션이 붙은 class는 제외한다. AppConfig가 자동으로 등록되면 안되기때문에
)
public class AutoAppConfig {

}
