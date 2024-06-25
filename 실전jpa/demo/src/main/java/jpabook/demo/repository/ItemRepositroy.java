package jpabook.demo.repository;


import jakarta.persistence.EntityManager;
import jpabook.demo.jpashop.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepositroy {

    private EntityManager em;

    public void save(Item item){
        if(item.getId()==null){
            em.persist(item);
        }
        else{
            em.merge(item);
        }
    }

    public Item finditem(Long id){
        return em.find(Item.class,id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }
}
