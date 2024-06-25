package jpabook.demo.repository;

import jakarta.persistence.EntityManager;
import jpabook.demo.jpashop.OrderSearch;
import jpabook.demo.jpashop.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private EntityManager em;

    public void save(Orders order){
        em.persist(order);
    }

    public Orders findOne(Long id){
        return em.find(Orders.class,id);
    }

    public List<Orders> findAll(OrderSearch orderSearch){
        return em.createQuery("select o from Orders o join o.member m where o.status=:staus and m.name like :name",Orders.class)
                .setParameter("status",orderSearch.getOrderStatus())
                .setParameter("name",orderSearch.getMembername())
                .getResultList();
    }
}
