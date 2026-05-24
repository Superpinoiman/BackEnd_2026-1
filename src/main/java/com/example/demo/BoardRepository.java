package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BoardRepository {
    private final Map<Integer, Board> boardMap = new HashMap<>();

    public Board save(Board board) {
        boardMap.put(board.getId(), board);
        return board;
    }

    public Optional<Board> findById(int id) {
        return Optional.ofNullable(boardMap.get(id));
    }
}

