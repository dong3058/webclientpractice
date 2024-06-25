package jpabook.demo.jpashop;


import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Book extends Item{

    public Book() {
    }

    private String author;

    private Long isbn;


}
