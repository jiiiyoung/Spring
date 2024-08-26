package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// ComponentScan을 붙이면 Configuration이 붙은 설정정보들이 자동으로 등록이 된다.
@Configuration
@ComponentScan(excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)) // 해당 정보는 앞서 지정된 설정이 있어서 임시로 제거한 것.
public class AutoAppConfig {

}
