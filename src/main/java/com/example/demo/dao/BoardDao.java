package com.example.demo.dao;

import com.example.demo.domain.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDao {

    @PersistenceContext
    private EntityManager em;

    public Board save(Board board) {
        em.persist(board);
        return board;
    }

    public Board findById(Long id) {
        return em.find(Board.class, id);
    }

    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class)
                .getResultList();
    }

    public void delete(Board board) {
        em.remove(board);
    }
}
