package com.example.chat;

import com.example.chat.entity.Boards;
import com.example.chat.entity.BoardsToShow;
import com.example.chat.entity.Comments;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
@RequiredArgsConstructor
public class Repository {

    private final EntityManager em;

    public void addboards(Boards b){

        em.persist(b);

    }



    public void addcomment(Comments comment){

        Boards boards=em.find(Boards.class,1);

        comment.setBoards(boards);

        em.persist(comment);

    }


    public List<BoardsToShow> getboardslist(){

        List<Boards> b=em.createQuery("select b from Boards b join b.comments")
                .getResultList();
        return b.stream()
                .map(x->new BoardsToShow(x.getId(),x.getText(),x.getMaintext(),x.getComments()))
                .collect(Collectors.toList());




    }

}
