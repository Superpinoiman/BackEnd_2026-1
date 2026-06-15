package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoard(int id) {
        return boardRepository.findById(id).orElse(null);
    }

    public Board createBoard(BoardRequest request) {
        Board board = new Board(
                boardRepository.nextId(),
                request.getName()
        );

        return boardRepository.save(board);
    }

    public Board updateBoard(int id, BoardRequest request) {
        Board oldBoard = boardRepository.findById(id).orElse(null);

        if (oldBoard == null) {
            return null;
        }

        Board updatedBoard = new Board(
                oldBoard.getId(),
                request.getName()
        );

        return boardRepository.save(updatedBoard);
    }

    public Board deleteBoard(int id) {
        Board board = boardRepository.findById(id).orElse(null);

        if (board == null) {
            return null;
        }

        boardRepository.delete(id);
        return board;
    }
}
