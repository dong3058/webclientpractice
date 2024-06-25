package jpabook.demo.service;

import jpabook.demo.jpashop.Item;
import jpabook.demo.repository.ItemRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private ItemRepositroy itemRepositroy;


    public void save(Item item){
        itemRepositroy.save(item);
    }


    public List<Item> itemlist(){
       return  itemRepositroy.findAll();
    }
    public Item itemone(Long id){
        return  itemRepositroy.finditem(id);
    }
}
