package hello.core.Scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)//TYPE은클래스 레벨에붙음.
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {//애가 붙은 애는 제외한다.
    //즉 지금 어노테이선 include exclude 두개를 만든것.
}
