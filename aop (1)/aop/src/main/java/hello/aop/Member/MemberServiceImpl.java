package hello.aop.Member;


import hello.aop.Member.annotation.ClassAop;
import hello.aop.Member.annotation.MethodAop;
import org.springframework.stereotype.Component;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService{
    @Override
    @MethodAop("test_value")
    public String hello(String param) {
        return "OK";
    }

    public String internal(String param){
        return "ok";
    }
}
