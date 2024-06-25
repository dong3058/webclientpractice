package com.example.webclientpractice;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
public class Member {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name="name")
    private String name;



    @OneToMany(fetch = FetchType.LAZY,mappedBy = "member")
    private List<Test> testlist;


    public Member() {
    }

    public Member(String name) {
        this.name = name;
    }



}
