package hello.aop;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder(){}//포인트컷 시그니처 public으로 바꾸면 단대서도 실행이가능하.



    @Pointcut("execution(* *..*Service.*(..))")
    public void AllService(){};


    @Pointcut("allOrder() && AllService()")
    public void orderAndService(){};

}
