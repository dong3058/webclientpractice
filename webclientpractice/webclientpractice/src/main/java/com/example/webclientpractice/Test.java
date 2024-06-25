package com.example.webclientpractice;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Entity
@Data
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;


    public Test(String name, Member member) {
        this.name = name;
        this.member = member;
    }
}
