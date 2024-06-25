package jpabook.demo.service;

import jakarta.persistence.EntityManager;
import jpabook.demo.jpashop.Member;
import jpabook.demo.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional

class MemberServiceTest {


    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    void jointest(){
        Member member1=new Member();

        Member member2=new Member();
        member2.setUsername("321");
        Long id2=memberService.join(member2);
        Long id=memberService.join(member1);

        Assertions.assertThat(id).isEqualTo(member1.getId());
    }

}