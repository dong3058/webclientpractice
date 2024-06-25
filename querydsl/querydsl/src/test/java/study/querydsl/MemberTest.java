package study.querydsl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static study.querydsl.QTeam.team;
import static study.querydsl.QUser.user;

@SpringBootTest
@Transactional
public class MemberTest {

    @Autowired
    EntityManager em;

    @Autowired
    TesetReposiotry tesetReposiotry;
    JPAQueryFactory jpaQueryFactory=new JPAQueryFactory(em);
    @BeforeEach
    public void befores(){
        Team teamA=new Team("teamA");
        Team teamB=new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);
        Member member1=new Member("member1",10,teamA);
        Member member2=new Member("member2",20,teamA);
        Member member3=new Member("member3",10,teamB);
        Member member4=new Member("member4",20,teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }


    @Test
    public void jparepositroywithquerydslrepo(){

        List<Member>members=tesetReposiotry.getmebmers();

        for(Member m: members){
            System.out.println(m.getId());
        }

    }
    @Test
    public void testsql(){


        JPAQueryFactory jpaQueryFactory=new JPAQueryFactory(em);

        QMember m = new QMember("m");
        Member findmember=jpaQueryFactory.select(m)
                .from(m)
                .where(m.username.eq("member1"))
                        .fetchOne();


        Assertions.assertThat(findmember.getUsername()).isEqualTo("member1");


    }
    @Test
    public void testsql2(){

        QMember m=new QMember("m");
        JPAQueryFactory jpaQueryFactory=new JPAQueryFactory(em);
        Member findmember=jpaQueryFactory.select(m)
                .from(m)
                .where(m.username.eq("member1").and(m.age.eq(10)))
                //and는 ,로 대체가능하며 특히나 조건은 null을 무시한다.
                .fetchOne();
    //jpql은 .eq,ne,.not(),in,notin,between,goe,gt,loe,lt,like,contains,startswith 같은걸 제공함.



    }
    @Test
    public void resultfetch(){
        JPAQueryFactory jpaQueryFactory=new JPAQueryFactory(em);
        List<Member> memlist=jpaQueryFactory.selectFrom(QMember.member)
                .orderBy(QMember.member.age.desc().nullsLast(),QMember.member.username.asc())
                .fetch();//애는 리스트 형태로

        //fetchone은 한개만 만약 없다면 null,중복시에는 에러발생.



    }

    @Test
    public void paging1(){
        JPAQueryFactory jpaQueryFactory=new JPAQueryFactory(em);
        List<Member> list=jpaQueryFactory.selectFrom(QMember.member)
                .orderBy(QMember.member.username.desc())
                .offset(1)
                .limit(2)
                .fetch();
        Assertions.assertThat(list.size()).isEqualTo(2);
    }
    @Test
    public void join1(){
        JPAQueryFactory jpaQueryFactory=new JPAQueryFactory(em);
                List<Member> mem=jpaQueryFactory.selectFrom(QMember.member)
                        .join(QMember.member.team,team)
                        .where(team.name.eq("teamA"))
                        .fetch();//이런방식

        /*              List<Member> mem=jpaQueryFactory
        .select(QMember.member)
        .from(member,team)

                        .where(member.username.eq(team.name))
                        .fetch();
                        세타 조인으로 from절에 2개를 나열하고
                        모든 회원 팀 테이블을 가져와서 전부다 조인을하고
                        where절에서 필터링을 함.*/



    }
    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    public void fetchjoin(){
        em.flush();
        em.clear();
        JPAQueryFactory jpaQueryFactory=new JPAQueryFactory(em);
        Member Member1=jpaQueryFactory.selectFrom(QMember.member)
                .where(QMember.member.username.eq("member1"))
                .fetchOne();
        //위의것은 멤버만 조회딤 즉 lazy인 team은 조회안됨.
        Assertions.assertThat(emf.getPersistenceUnitUtil().isLoaded(Member1.getTeam())
        ).isFalse();//로딩이 된건지 체크하는거 -->여기선 로딩이안되므로false임.
    }


    @Test
    public void fetchjoin2(){
        em.flush();
        em.clear();
        JPAQueryFactory jpaQueryFactory=new JPAQueryFactory(em);
        Member members=jpaQueryFactory.select(QMember.member)
                .from(QMember.member)
                .join(QMember.member.team,team).fetchJoin()
                .where(QMember.member.username.eq("member1"))
                .fetchOne();
        //위의것은 멤버만 조회딤 즉 lazy인 team은 조회안됨.
        Assertions.assertThat(emf.getPersistenceUnitUtil().isLoaded(members.getTeam())
        ).isTrue();//로딩이 된건지 체크하는거 -->여기선 로딩이안되므로false임.

    }

    @Test
    public void subquery(){
        QMember submember=new QMember("submember");
       jpaQueryFactory.selectFrom(QMember.member)
               .where(QMember.member.age.in(
                       JPAExpressions
                               .select(submember.age)
                               .from(submember)
                               .where(submember.age.gt(10))
               ))
               .fetch();
    }

    @Test
    public void casequery(){
            List<String> result=jpaQueryFactory.select(QMember.member.age.when(10).then("열살")
                            .otherwise("암거나"))
                    .from(QMember.member)
                .fetch();

        //w조금 복잡한 버전

        //casebuidler-->select(new CaseBuilder().when(member.age.between(0,20)).then(0~20)))
        //이렇게쓴다 조금 복잡한 애들 버전.
    }

    @Test
    public void tuple(){
        //tuple은 프로젝션댕상이2개 즉 select문에 들어가는대상이 여러개일떄를의미.

        List<String> result=jpaQueryFactory.select(QMember.member.username)
                .from(QMember.member)
                .fetch();//이렇게 멤버이라는 한개의대상만.
        List<Tuple> result2=jpaQueryFactory.select(QMember.member.username,QMember.member.age)
                .from(QMember.member)
                .fetch();
        for(Tuple x: result2){
            x.get(QMember.member.username);
            x.get(QMember.member.age);

            //이렇게조회하는건대 그냥 dto쓰는게 맘편할듯 ㅇㅇ;
        }
    }

    @Test
    public void dto(){

        jpaQueryFactory.select(Projections.bean(MemberDto.class,QMember.member.username,QMember.member.age)).
                from(QMember.member)
                .fetch();
        //이방식은 기본생성자가 필수ㅡ임. 또한 setter도 필요
        jpaQueryFactory.select(Projections.fields(MemberDto.class,QMember.member.username,QMember.member.age)).
                from(QMember.member)
                .fetch();
        QMember membersub=new QMember("membesub");
        jpaQueryFactory.select(Projections.fields(MemberDto.class,QMember.member.username,
                        ExpressionUtils.as(JPAExpressions.select(membersub.age.max())
                                .from(membersub),"age")
                //이렇게하면 집계함수의결과처럼 이름을 주기애매한애들을 넣을수가있다.

                )).
                from(QMember.member)
                .fetch();
        //애는 생성자가 상관업이 getter setter 무시하고 그냥 값이 드어감. 단 필드명 일치가 필요함.
        //즉 만약 dto의 필드명이다르다면 .as("name")이렇게 별칭을 지어주자.
        jpaQueryFactory.select(Projections.constructor(MemberDto.class,QMember.member.username,QMember.member.age)).
                distinct().//distonct는 jpa의 그것과 동일함 ㅇㅇ'
        from(QMember.member)
                .fetch();
        //애는 생성자 방식. 생성자가 필요.기본생성자포함.
        //근대애는 위의 fieds방식과는다르게 생성자로 들어가느거라 막 별칭해주고할필요가없다
        //둘다 차이점이있다면은 consrucor방식은 컴파일 에러를 잡지못한다
        //즉 위에서 인수한개를 더넣어도 일단 실행은 된다는것이다.

        /*jpaQueryFactory.select(new QMemberDto(~~~))
                .from(QMember.member)
                .fetch();*/
    }


    @Test
    public void dynamicquery(){
        String username="member1";
        Integer age=10;

        List<Member> result=searchMember2(username,age);
    }


    private List<Member> searchMember2(String username,Integer age){
        return jpaQueryFactory.selectFrom(QMember.member)
                .where(usernameEq(username),ageEq(age))
                .fetch();
    }


    //벌크연산?--->영속성 컨텍스트를 무시하고 db를 업데이트 하는행위
    //즉 벌크 연산후에는 영속성 컨텍스트를 초기화 해주도록하자.
    private Predicate usernameEq(String username){
        if(username==null){
            return null;
        }
        return QMember.member.username.eq(username);
    }
    private Predicate ageEq(Integer age){
        if(age==null){
            return null;
        }
        return QMember.member.age.eq(age);
    }



    private BooleanExpression usernameEq2(String username){
        if(username==null){
            return null;
        }
        return QMember.member.username.eq(username);
    }
    private BooleanExpression ageEq2(Integer age){
        if(age==null){
            return null;
        }
        return QMember.member.age.eq(age);
    }

    private BooleanExpression eq(String username,Integer age){
        return usernameEq2(username).and(ageEq2(age));
    }//이렇게 조립도 가능 단 booleanexpression을 써야됨.


    @Test
    public void bulkupdate(){
        long count=jpaQueryFactory
                .update(QMember.member)
                .set(QMember.member.username,"zz")
                //.set(QMember.member.age,QMember.member.age.add(1))-->조건에 해당되는 멥버의 데이터수정.
                .where(QMember.member.age.eq(10))
                .execute();
        //업데이트하고 나서 영향받은 애의 갯수를 돌려줌.
        //단 벌크연산이므로 영속성 컨텍스트를 무시하고 다이렉트로 실행한다
        //즉 영속성 컨텍스트를 상황에따라선 초기화를 해줄필요도 존재한다.
        //왜냐면 업데이트된 db에서 가져와도 이미 영속성컨텍스트에있다면 그걸 따라가기떄문.

        /*jpaQueryFactory
                .delete(member)
                .where(member.age.ge(10))
                .execute();//-->지우는 벌크연산.
        */
    }




    @Controller
    @Slf4j
    public class Oauth2Controller {
        private RedisTemplate<String,String> redisTemplate;

        private MemberJpaRepository memberJpaRepository;

        private JwtUtill jwtUtill;

        private EntityManager em;
        private MemberService memberService;
        @Autowired
        public Oauth2Controller(@Qualifier("redisTemplate") RedisTemplate<String,String> redisTemplate, JwtUtill jwtUtill, MemberService memberService
                , MemberJpaRepository memberJpaRepository, EntityManager em){
            this.redisTemplate=redisTemplate;
            this.memberJpaRepository=memberJpaRepository;
            this.jwtUtill=jwtUtill;
            this.memberService=memberService;
            this.em=em;
        }
        @GetMapping("/kakaologin")
        public void kakaologin(HttpServletResponse resp)throws IOException {
            resp.sendRedirect("/oauth2/authorization/kakao");


        }



        @GetMapping("/googlelogin")
        public void googlelogin(HttpServletResponse resp)throws IOException {
            resp.sendRedirect("/oauth2/authorization/google");
        }


        @GetMapping("/jparepotest")
        @ResponseBody
        public Page<Member> testing(){
            for(int i=0;i<10;i++){
                String sts=String.valueOf(i);
                Member member=new Member(sts,sts);
                memberJpaRepository.save(member);
            }


            Optional<Member> member=memberJpaRepository.findById(1L);
            log.info("member:{}",member.get());

            List<Member> members=memberJpaRepository.findAll();
            for(Member m:members){
                log.info("memers:{}",m);
            }


            Pageable pageable= PageRequest.of(2,3);
            //페이지 넘버는 0으로 시작 paigsize 갯수만큼 데이터를 가져오는거고
            //getoffset--->은 pagenumber*pagesize꼴임. 즉 0이ㅇ면 0부터시작해서 123
            //1일경우 offest은3 즉 456을 가져온다는 의미.




            Page<Member> members2=memberJpaRepository.findAll(pageable);

            Page<Long> y=members2.map(x->x.getMemberId());
            //음 스트림의 map과같은기능. page꼴로 타입이 저장되이어ㅣㅆ으나 사실상
            //foreach로 long타입으로 조회가 가능함!


            log.info("pagedata:{}{}",pageable.getOffset(),pageable.getPageNumber());

            for(Long x:y){
                log.info("idx:{}",x);
            }


            JPAQueryFactory jpaQueryFactory=new JPAQueryFactory(em);

            List<Member> memberss2=jpaQueryFactory.selectFrom(QMember.member)
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
x
            return new PageImpl<>(memberss2,pageable,3L);
            //이렇게하면은 프론트에서 json의 content propertis로 내용에접근이가능
            //return members2.map(x->new MemberDto(x.getEmail(),x.getName()));--->위으 pageimpl도같음.
        }







    }




