package hello.advance.proxy.v_4postprocessor;


import hello.advance.proxy.factory.LogTraceAdvice;
import hello.advance.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsingAdvisorBeanAndSpringBeanpostprocessor {
//애는 스프링의 자동 프록시 생서기를 이용하는것.쓰고싶으면 그랴들에 뭐좀 등록해야됨
// 이러면 빈후처리기도 따로정할필요벗이
    //어드바이저만 빈으로 등록하면 그만이다.
    //이과정에서 포인트컷이 이용되는대
    // 자동 생성기는 빈으로 등록된 모든 어드바이저를 객체에대해서 비교해서 객체가 가진
    //메서드중에 포인트컷과 일차할경우 어드바이저를 해당 객체의 프록시에 걸어둔다
    //추가로 한개의 객체가 여러 어드바이저가 유효하면 한개의 프록시객체에다가 어드바이저를 여러개를 걸어둔다.
    //만일 일치하지않으면 그냥 원본객체를 빈으로 등록함.
    //후에 프록시 객체를 이용시에 호출한 메서드가 프록시의 어드바이저의 조건에 일치할경우
    //어드바이저가 실행된다.
    //즉 등록할 객체와 프록시를 추후에 실행할떄 메서드가 어드바이저를 타고가는지를 구분하는대
    //포인트컷을 쓴다는것.
    private Advisor getAdvisor(LogTrace logTrace){
        NameMatchMethodPointcut pointcut=new NameMatchMethodPointcut();
        pointcut.setMappedNames("request","order","save");

        LogTraceAdvice logTraceAdvice=new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut,logTraceAdvice);
    }
    private Advisor getAdvisor2(LogTrace logTrace){
        AspectJExpressionPointcut aspectJExpressionPointcut=new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression("execution(* hello .proxy.app..*(..) )");
        //프록시를 위한 표현식이라는대 차후에 설명한다고한다.
        LogTraceAdvice logTraceAdvice=new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(aspectJExpressionPointcut,logTraceAdvice);
    }

}
