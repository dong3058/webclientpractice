package jpabook.demo.service;


import jpabook.demo.jpashop.Member;
import jpabook.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@RequiredArgsConstructor
@Transactional(readOnly = true)//이렇게 설정하면 리소스를 덜먹음.
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long join(Member member){
        sameccount(member);
        return memberRepository.save(member);
    }


    private void sameccount(Member member){
        List<Member> m=memberRepository.findByName(member.getUsername());
        if(!m.isEmpty()){
            throw new IllegalStateException("이미잇는 이름입니다");
        }
    }


    public List<Member> findmemberlist(){
        return memberRepository.findAll();

    }

    public Member findOne(Long id){

        return memberRepository.findOne(id);
    }




}
