package hello.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

@Slf4j
@Aspect
public class AspectV6 {
    //참고로 모든 어드바이스는 첫번쨰인수ㅗ joinpoint를 받을수있으며
    //procedjoinpoint는 애의 하위 이다.
    //프로시딩 조인포인트에는 함수를 실행할 proceed가 들어가있는셈이다(추가된것). 단 around에서만 프로시딩만써야된다.
    //또한 around는 proced를 호출하지않으면 다음 코드가 실행이되지않는다.
    //그러나 beofre afeter애내들은 자동으로 target이 실행되면서 넘어가는애들이다.



    /*public static class Transcation{
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

    }*/
    @Before("hello.aop.Pointcuts.orderAndService()")
    public void dobefore(JoinPoint joinPoint){
        log.info("before {}",joinPoint.getSignature());


        //즉 proced는 알아서 작성이되고 그이전의 로직만 작성한다는 말이다.
        //Proceedingjoinpoint.proceed가 알아서 이함수가 끝나고 실행된다는말.
    }
    @AfterReturning(value="hello.aop.Pointcuts.orderAndService()",returning = "result")
    public void dobefore(JoinPoint joinPoint,Object result){

        log.info("afterreturn {} result {}",joinPoint.getSignature(),result);


        //애도 procedd는 알아서 작동하고 그결과를 returning result 로 받은후 클래스의 인수로주어진 result로받는다.
        //단이떄 이름은 같게해야됨.즉 returning에 들어간 이름과 어드바이스 매개변수의 이름과 같아야디ㅗㅁ.

        //현재애는 ordreservice의 메서드에 결러있고
        //그 메서드의 반환 타입이 void이므르ㅗ object 타입으로 받아야 받을수가있다.
        //만약 타입이 맞지않는다면 즉 반환값과 타입이 안맞으면 아예 afterreturning이 호출이안됨.
        //또한 반환되는 객체를 조작해서 반환은 불가 단 코드내에서는 조작을 가할수가있다.
        //즉 @around는 함수의 실행결과를 조작해서 넘기는게 가능하나 ㅇ는 불가느앟다이말.
    }

    @AfterThrowing(value="hello.aop.Pointcuts.orderAndService()",throwing = "ex")
    public void dothrow(JoinPoint joinPoint,Exception ex){

        log.info("afterthrowing  ex {}",joinPoint.getSignature(),ex);

    //자동으로 throw e가됨.

        //애도 위의 afterreturning처럼 타입이 맞아야됨.

    }
    @After(value="hello.aop.Pointcuts.orderAndService()")
    public void doafter(JoinPoint joinPoint){

        log.info("after {}",joinPoint.getSignature());



    }

}
