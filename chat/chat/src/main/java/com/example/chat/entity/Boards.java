package com.example.chat.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Boards {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String text;
    @Column
    private String maintext;



    @OneToMany(mappedBy="boards")
    private List<Comments> comments=new ArrayList<>();

    public void setText(String text) {
        this.text = text;
    }

    public void setMaintext(String maintext) {
        this.maintext = maintext;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getMaintext() {
        return maintext;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public Boards() {

    }
}
