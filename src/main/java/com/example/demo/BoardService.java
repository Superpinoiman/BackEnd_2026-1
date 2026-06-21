package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    private final BoardDao boardDao;
    private final ArticleDao articleDao;

    public BoardService(BoardDao boardDao, ArticleDao articleDao) {
        this.boardDao = boardDao;
        this.articleDao = articleDao;
    }

    public List<Board> getAllBoards() {
        return boardDao.findAll();
    }

    public Board getBoard(Long id) {
        return boardDao.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Board createBoard(BoardRequest request) {
        Long id = boardDao.insert(request);

        return boardDao.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Board updateBoard(Long id, BoardRequest request) {
        boardDao.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));

        boardDao.update(id, request);

        return boardDao.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Board deleteBoard(Long id) {
        Board board = boardDao.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));

        if (articleDao.existsByBoardId(id)) {
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }

        boardDao.deleteById(id);
        return board;
    }
}
