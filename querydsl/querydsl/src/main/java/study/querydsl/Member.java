package study.querydsl;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="team_id")
    private Team team;




    public Member() {
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        this.team = team;
    }
}
