package com.example.chat.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardsToShow {


    private Long id;

    private String title;

    private String maintext;

    private List<Comments> c=new ArrayList<>();


    public BoardsToShow(Long id, String title, String maintext, List<Comments> c) {
        this.id = id;
        this.title = title;
        this.maintext = maintext;
        this.c = c;
    }
}
