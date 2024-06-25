package jpabook.demo.jpashop;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Member {

    public Member() {
    }

    @Id
    @GeneratedValue
    private Long id;
    private String username;


    @Embedded
    @Column(name="address")
    Address addresss;

    @OneToMany(mappedBy ="member",fetch= FetchType.LAZY,orphanRemoval = true)

    private List<Orders> ordersList=new ArrayList<>();

}
