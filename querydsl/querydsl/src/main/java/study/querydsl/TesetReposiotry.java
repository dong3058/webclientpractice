package study.querydsl;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TesetReposiotry extends JpaRepository<Member,Long>,QueryDslRepository{
    //저기 뒤에있는 리포지토리는 인터페이스이지만 애의 구현체로 querydsl을 만들고
    //그걸 다형성으로 넣은거임 ㅇㅇ
}
