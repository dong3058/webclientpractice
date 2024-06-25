package jpabook.demo;

import jakarta.persistence.EntityManager;
import jpabook.demo.jpashop.Member;
import jpabook.demo.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.parser.Entity;


@SpringBootTest
class MemberRepositoryTest {



    @Autowired
    MemberRepository memberRepository;


    @Test
    public void testmember(){
        Member member=new Member();
        member.setUsername("Zz");
        Long save= memberRepository.save(member);
        Member member1=memberRepository.findOne(save);


        Assertions.assertThat(member1.getId()).isEqualTo(member.getId());
    }


}