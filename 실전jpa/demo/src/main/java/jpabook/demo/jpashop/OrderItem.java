package jpabook.demo.jpashop;


import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.core.annotation.Order;

@Entity
@Data
public class OrderItem {

    public OrderItem() {
    }

    @Id
    @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Orders orders;


    private int orderprice;


    private int count;

    public static OrderItem createorderItem(Item item,int orderprice,int count){
        OrderItem orderItem=new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderprice(orderprice);
        orderItem.setCount(count);

        item.removestock(count);
        return orderItem;
    }
    public void cancel(){
        getItem().addstock(count);
    }

}
