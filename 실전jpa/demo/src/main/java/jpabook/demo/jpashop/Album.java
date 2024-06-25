package jpabook.demo.jpashop;


import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Album extends Item{

    public Album() {
    }

    private String artist;


}
