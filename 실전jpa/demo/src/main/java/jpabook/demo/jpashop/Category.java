package jpabook.demo.jpashop;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category {
    public Category() {
    }

    @Id
    @GeneratedValue
    @Column(name="category_id")
    private Long id;

    private String name;


    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)

    private List<CategoryandItem> categoryandItem=new ArrayList<>();



    @ManyToOne
    @JoinColumn(name="parent_id")
    private  Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child=new ArrayList<>();
}
