package jpabook.demo.jpashop;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CategoryandItem {

    public CategoryandItem() {
    }

    @Id
    @GeneratedValue
    @Column(name="categoryanditem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;
}
