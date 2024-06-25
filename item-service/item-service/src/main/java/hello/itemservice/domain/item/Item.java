package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Data는 뭔가 예측하지못하ㅔ 작동할지도.
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;


    public Item(){

    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
