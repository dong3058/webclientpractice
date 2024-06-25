package com.example.chat;

import com.example.chat.entity.Boards;
import com.example.chat.entity.BoardsToShow;
import com.example.chat.entity.Comments;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.stream.events.Comment;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class proxytest {

    @Autowired
    private Repository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testProxyLoading() {
        // given
        Boards b=new Boards();
        b.setMaintext("111");
        b.setText("hi");
        entityManager.persist(b);

        Comments c=new Comments();
        c.setMaintext("ccc");
        c.setBoards(b);

        entityManager.persist(c);

        entityManager.flush();
        entityManager.clear();

        // when


        List<BoardsToShow > b_list =repository.getboardslist();

        // then
        List<Comments> c_list=b_list.get(0).getC();

        for(Comments x:c_list){
            assertFalse(isProxy(c_list));
        }


    }

    private boolean isProxy(Object entity) {
        return Hibernate.isInitialized(entity) && entity instanceof HibernateProxy;
    }
}
