package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {


    MemberService memberService;

    @BeforeEach

    public void beforeEach(){
        AppConfig appconfig=new AppConfig();
        memberService=appconfig.memberService();

    }

    @Test

    void join(){
        Member member=new Member(1L,"memberA",Grade.VIP);
        memberService.join(member);
        Member findMember=memberService.findMember(1L);


        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
