package hello.itemservice.domain.item;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long,Item> store =new HashMap<>();
    //해시맵쓰면아됨. 멀티스레드에서 동시접속시 문제생김
    //아래의 sequene도마찬가지.

    private static Long sequence=0L;


    public Item ItemSave(Item item){
        item.setId(++sequence);
        store.put(item.getId(),item);


        return item;

    }

    public Item FindItem(Long id){


        return store.get(id);
    }

    public List<Item> FindAll(){
        return  new ArrayList<>(store.values());

    }

    public void update(Long id,Item item){

        Item items=FindItem(id);
        items.setItemName(item.getItemName());
        items.setPrice(item.getPrice());

        items.setQuantity(item.getQuantity());

    }

    public void clear(){
        store.clear();
    }
}
