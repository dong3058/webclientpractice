package hello.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j

//어드바이스의 순서는 @aspect다단ㅇ위로 @order를 통해서 정해짐
//즉 안ㅇ있는 두개의 순서를 정하고싶으면 각각 따로 클래스르 만들어줘야된다는말.
//여기선 그냥 안에다가만든것
public class AspectV5 {


    @Aspect
    @Order(1)
    public static class logaspect{
        @Around("hello.aop.Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinpoint) throws Throwable{
            log.info("log {}",joinpoint.getSignature());
            return joinpoint.proceed();
        }

    }

    @Aspect
    @Order(2)
    public static class Transcation{
        @Around("hello.aop.Pointcuts.orderAndService()")//두개의 범위를 만족하는애들을 찾는다.
        public Object doTransaction(ProceedingJoinPoint joinpoint) throws Throwable{
            try{
                //@Before에 해당 즉 조인 포인트 실행이전
                log.info("트랜잭션시작 {}",joinpoint.getSignature());
                Object result=joinpoint.proceed();//-->이게 조인포인트 실행임.
                //@afterreutrning-->조인 포인트 실행이후
                log.info("트랜잭션 커밋 {}",joinpoint.getSignature());
                return result;
            }
            catch(Exception e){
                //@afterthorwing 에러 발생후 즉 아래 영역
                log.info("트랜잭션 롤백 {}",joinpoint.getSignature());
                throw e;
            }
            finally{
                //@after->조인포인트의 정상 비정상 실행 여부를 구분하지않고 코드를 작성
                log.info("리소스 릴리즈 {}",joinpoint.getSignature());
            }
        }

    }

}
