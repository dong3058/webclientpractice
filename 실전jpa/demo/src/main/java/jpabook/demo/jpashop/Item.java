package jpabook.demo.jpashop;


import jakarta.persistence.*;
import jpabook.demo.exception.NotEnoughStockException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@Data
public class Item {


    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;

    private int price;

    private  int stockQuantity;

    @OneToMany(mappedBy = "item",fetch = FetchType.LAZY)
    private List<OrderItem> orderItem=new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "item")
    private List<CategoryandItem> categoryandItem=new ArrayList<>();

    public void addstock(int q){
        this.stockQuantity+=q;
    }

    public void removestock(int q){
        if(this.stockQuantity-q<0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity-=q;
    }




}
