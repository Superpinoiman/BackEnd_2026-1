package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BoardRepository {
    private final Map<Integer, Board> boardMap = new HashMap<>();
    private int lastId = 1;

    public BoardRepository() {
        boardMap.put(1, new Board(1, "자유게시판"));
    }

    public int nextId() {
        return ++lastId;
    }

    public Optional<Board> findById(int id) {
        return Optional.ofNullable(boardMap.get(id));
    }

    public List<Board> findAll() {
        return new ArrayList<>(boardMap.values());
    }

    public Board save(Board board) {
        boardMap.put(board.getId(), board);
        return board;
    }

    public void delete(int id) {
        boardMap.remove(id);
    }
}
