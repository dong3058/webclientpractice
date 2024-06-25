package hello.core.annotation;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
//@Inherited
//@Documented
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE,ElementType.ANNOTATION_TYPE,ElementType.PARAMETER})
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
