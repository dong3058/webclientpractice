package com.example.chat;

import com.example.chat.entity.Boards;
import com.example.chat.entity.BoardsToShow;
import com.example.chat.entity.Comments;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional
@RequiredArgsConstructor
public class Service {

    private final Repository repository;


    public void makeboards(Boards b){

        repository.addboards(b);

    }

    public void makecommetns(Comments c){

        repository.addcomment(c);
    }



    public List<BoardsToShow> boardslist(){



        return repository.getboardslist();
    }
}
