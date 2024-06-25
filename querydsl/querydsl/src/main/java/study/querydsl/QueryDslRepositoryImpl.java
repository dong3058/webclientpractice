package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static study.querydsl.QMember.member;

@Repository
public class QueryDslRepositoryImpl implements QueryDslRepository{

    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    public QueryDslRepositoryImpl(EntityManager em){

        this.jpaQueryFactory=new JPAQueryFactory(em);
    }


    @Override
    public List<Member> getmebmers() {
        return jpaQueryFactory.selectFrom(member)
                .fetch();
    }
}
