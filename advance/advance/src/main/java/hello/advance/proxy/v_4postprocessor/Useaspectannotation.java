package hello.advance.proxy.v_4postprocessor;

import hello.advance.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@RequiredArgsConstructor
//이 클래스를 빈으로 등록시에 어드바이저로 작동한다.즉 그냥 직접 만들던 어드바이저를 쉽게 만들게 해준다는말이다.
//그러니까 이클래스가 빈으로 등록시 @aspect라는 어노테이션을 통해서 애를 어드바이저로 등록한다는말.
//즉애는 스프링이 어떤 빈을 생성할떄 자동 프록시 생성시기에서 aspect라는 어노테이션이 붙은 모든 애를
//다 뒤져보고 이걸 통해서 이이하의 과정을 진행한다는말.
public class Useaspectannotation {
    private final LogTrace logTrace;


    @Around("execution(* ~~)")//포인트카슬 어노테이션으로 주고 아래가 어드바이스에 해당됨.
    //joinpoint.gettarget,getarges,getsingnature로 실제 호출대상,인수 등을 얻을수가있다.
    //즉 그냥 프록시 팩토리의 invocation이다.
    public Object execute(ProceedJoinPoint joinPoint) throws Throwable{

    }


}
