package hello.core.Scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class Componenttest {

    @Test
    void filerscan(){
        ApplicationContext ac =new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        Beana ba =ac.getBean("beana",Beana.class);
        Assertions.assertThat(ba).isNotNull();
        Beanb bb=ac.getBean("beanb",Beanb.class);
        Assertions.assertThat(bb).isNotNull();//애는 오류 왜냐 exclude시켰으니까.
    }

    @Configuration
    @ComponentScan(//필터타입에는 5가지가있는대 어노테이션(기본값),assignable_type=>지정한타입과 자식인식
            // ,aspectj,regex=>정규표현식,custom=>TypeFiler라는 인터페이스로 구현.
            includeFilters = @ComponentScan.Filter(type=FilterType.ANNOTATION,classes=MyIncludeComponent.class),
            //excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes=MyExcludeComponent.class)
            excludeFilters = {@ComponentScan.Filter(type= FilterType.ANNOTATION,classes=MyExcludeComponent.class),
            @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE,classes=Beana.class)
            } //이렇게 여러개 등록.

    )

    static class ComponentFilterAppConfig{

    }
}
