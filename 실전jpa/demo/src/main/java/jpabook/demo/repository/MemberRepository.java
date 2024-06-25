package jpabook.demo.repository;


import jakarta.persistence.EntityManager;
import jpabook.demo.jpashop.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@RequiredArgsConstructor
public class MemberRepository {

    private EntityManager em;

    public MemberRepository(EntityManager em) {
        this.em = em;
    }

    @Autowired

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        String query = "select m from Member m";
        return em.createQuery(query, Member.class).getResultList();
    }

    public List<Member> findByName(String name){


    return em.createQuery("select m from Member m where m.username=:name",Member.class)
            .setParameter("name",name)
            .getResultList();
    }




}
