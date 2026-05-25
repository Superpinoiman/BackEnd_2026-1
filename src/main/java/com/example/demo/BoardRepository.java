package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BoardRepository {
    private final Map<Integer, Board> boardMap = new HashMap<>();

    public BoardRepository() {
        boardMap.put(1, new Board(1, "자유게시판"));
    }

    public Optional<Board> findById(int id) {
        return Optional.ofNullable(boardMap.get(id));
    }

    public Board getDefaultBoard() {
        return boardMap.get(1);
    }

}

