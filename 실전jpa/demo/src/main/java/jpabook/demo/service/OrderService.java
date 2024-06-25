package jpabook.demo.service;

import jpabook.demo.jpashop.*;
import jpabook.demo.repository.ItemRepositroy;
import jpabook.demo.repository.MemberRepository;
import jpabook.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {


    private OrderRepository orderRepository;
    private MemberRepository memberRepository;
    private ItemRepositroy itemRepositroy;

    public Long order(Long memberId,Long itemid,int count){
        Member member=memberRepository.findOne(memberId);
        Item item=itemRepositroy.finditem(itemid);

        Delivery delivery=new Delivery();
        delivery.setAddress(member.getAddresss());

        OrderItem orderItem=OrderItem.createorderItem(item,item.getPrice(),count);

        Orders order=Orders.createorder(member,delivery,orderItem);

        orderRepository.save(order);

        return order.getId();
    }


    public void cancelorder(Long orderid){

        Orders order=orderRepository.findOne(orderid);

        order.cancel();;

    }

    public List<Orders> findorders(OrderSearch orderSearch){
        return orderRepository.findAll();
    }
}
