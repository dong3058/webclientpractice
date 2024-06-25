package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {


    ItemRepository itemrepo=new ItemRepository();
    @AfterEach
    void clear(){
        itemrepo.clear();
    }
    @Test
    public void itemtest(){
        Item item=new Item("a",1,2);
        itemrepo.ItemSave(item);
        Item finditem=itemrepo.FindItem(item.getId());


        Assertions.assertThat(finditem).isEqualTo(item);

    }

    @Test
    public void  findall(){
        Item item1=new Item("a",1,2);
        Item item2=new Item("B",1,2);
        itemrepo.ItemSave(item1);
        itemrepo.ItemSave(item2);
        List<Item> arrays=itemrepo.FindAll();

        Assertions.assertThat(arrays.size()).isEqualTo(2);
        Assertions.assertThat(arrays).contains(item1,item2);
    }
    @Test
    public void updateitem(){
        Item items=new Item("a",1,2);

        itemrepo.ItemSave(items);

        Item items2=new Item("b",2,3);
        itemrepo.update(items.getId(),items2);

        Assertions.assertThat(items.getItemName()).isEqualTo("b");
        Assertions.assertThat(items.getPrice()).isEqualTo(2);
        Assertions.assertThat(items.getQuantity()).isEqualTo(3);


    }

}