package com.example.chat;



import com.example.chat.entity.Boards;
import com.example.chat.entity.BoardsToShow;
import com.example.chat.entity.Comments;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class Controller {

    private final Service service;

    @PostMapping("/saveboard")
    public void makeborad(@ModelAttribute Boards b){


        service.makeboards(b);

    }

    @PostMapping("/savecomment")
    public void makecomment(@ModelAttribute Comments c){
        service.makecommetns(c);
    }


    @PostMapping("/getboardlist")
    public List<BoardsToShow> boardlist(){

        return service.boardslist();
    }
}
