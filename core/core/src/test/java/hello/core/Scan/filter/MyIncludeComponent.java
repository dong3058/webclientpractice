package hello.core.Scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)//TYPE은클래스 레벨에붙음.
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {//애가 붙은거는 컴포너넌트 스캔에 추가한다.
}
