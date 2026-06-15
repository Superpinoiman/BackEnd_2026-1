package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final ArticleRepository articleRepository;

    public BoardService(BoardRepository boardRepository, ArticleRepository articleRepository) {
        this.boardRepository = boardRepository;
        this.articleRepository = articleRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoard(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));
    }

    public Board createBoard(BoardRequest request) {
        Board board = new Board(
                boardRepository.nextId(),
                request.getName()
        );
        return boardRepository.save(board);
    }

    public Board updateBoard(int id, BoardRequest request) {
        Board oldBoard = boardRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));

        Board updatedBoard = new Board(
                oldBoard.getId(),
                request.getName()
        );

        return boardRepository.save(updatedBoard);
    }

    public Board deleteBoard(int id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));

        if (articleRepository.existsByBoardId(id)) {
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }

        boardRepository.delete(id);
        return board;
    }
}
