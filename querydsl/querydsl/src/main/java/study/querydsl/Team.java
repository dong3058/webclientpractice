package study.querydsl;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data

public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members;

    public Team() {
    }


    public Team(String name) {
        this.name = name;

    }
}
