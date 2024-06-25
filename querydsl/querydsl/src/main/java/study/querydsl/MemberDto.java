package study.querydsl;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberDto {
    private String username;
    private int age;


    public MemberDto() {
    }

    @QueryProjection//이렇게하면 dto도 q파일로 생성이ㅗ딘다.
    //근대 이방식은 기술 종속적 즉 쿼리 dsl에 종속된단느 문제가 존재
    //음 그냥 projectiosn.constructor을 쓰면될듯.
    public MemberDto(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
