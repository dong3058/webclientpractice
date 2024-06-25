package com.example.chat.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;

@BatchSize(size=2)
@Entity
public class Comments {

    @Id
    @GeneratedValue
    private Long id;


    @Column
    private String maintext;

    //private LocalDateTime date;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Boards boards;


    public void setMaintext(String maintext) {
        this.maintext = maintext;
    }

    public void setBoards(Boards boards) {
        this.boards = boards;
    }

    public Long getId() {
        return id;
    }

    public String getMaintext() {
        return maintext;
    }

    /*public LocalDateTime getDate() {
        return date;
    }*/
}
