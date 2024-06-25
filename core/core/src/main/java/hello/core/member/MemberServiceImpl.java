package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component//빈 등록없이 자동으로 등록이 즉 @bean을 쓸필요가없어짐.

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;//= new MemoryMemberRepository();

    @Autowired//생성자에 붙여주면 자동적으로의존관계주입을 해주게된다. ac.getbean처럼 등록해준다는말.
    //즉 이전 appconfing는 직접 의존관계를 넣고 대입을 해주지만 이젠 안그래도됨.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // autowired이므로 memberrepo에맞는 스프링 빈 즉 자식타입인 memoryMember~로등록된 빈을 찾아서 대입한다.
    //이때 찾는 방식은 getbean(MemberRepository.class)이런꼴이다. 당연하게도 저클래스의 빈이 여러개면 오류가난다.
    //그건 뒤에서
    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    //싱글톤 테스트용.
    public MemberRepository getMemberRepository(){
        return  memberRepository;
    }
}