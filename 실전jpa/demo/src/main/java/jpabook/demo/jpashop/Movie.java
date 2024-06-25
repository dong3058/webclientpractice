package jpabook.demo.jpashop;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue(value="movie")
public class Movie extends Item{

    public Movie() {
    }

    private String director;


}
