package jpabook.demo.jpashop;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Delivery {
    @Id
    @Column(name="delivertid")
    private Long id;


    @OneToOne(fetch = FetchType.LAZY,mappedBy = "delivery")
    @JoinColumn(name="order_id")
    private Orders orders;


    @Embedded
    Address address;


    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus deliveryStatus;


}
