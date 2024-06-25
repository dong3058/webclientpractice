package jpabook.demo.jpashop;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Orders {


    public Orders() {
    }

    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "orders",fetch =FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList=new ArrayList<>();

    private LocalDateTime order_date;


    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;


    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    public void addorder(OrderItem orderItem){
        this.orderItemList.add(orderItem);
        orderItem.setOrders(this);
    }
    public static Orders createorder(Member member,Delivery delivery,OrderItem... orderItems){
        Orders orders=new Orders();
        orders.setMember(member);
        orders.setDelivery(delivery);
        for(OrderItem o :orderItems){
            orders.addorder(o);
        }

        orders.setOrder_date(LocalDateTime.now());

        orders.setOrderStatus(OrderStatus.Order);

        return orders;
    }

    public void cancel(){
        if(delivery.getDeliveryStatus()==DeliveryStatus.comp){
            throw new IllegalStateException("배소오안료된 상품은 취소가 불가능합니다");

        }
        this.setOrderStatus(orderStatus.Cancle);
        for(OrderItem o:orderItemList){
            o.cancel();
        }
    }

    public int ordertotprice(){
        int totprice=0;
        for(OrderItem o:orderItemList){
            totprice+=o.getOrderprice()*o.getCount();
        }

        return totprice;
    }
}
