package com.example.demo.service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.BoardDao;
import com.example.demo.domain.Board;
import com.example.demo.dto.BoardRequest;
import com.example.demo.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BoardService {

    private final BoardDao boardDao;
    private final ArticleDao articleDao;

    public BoardService(BoardDao boardDao, ArticleDao articleDao) {
        this.boardDao = boardDao;
        this.articleDao = articleDao;
    }

    @Transactional
    public Board createBoard(BoardRequest request) {
        Board board = new Board(request.getName());
        return boardDao.save(board);
    }

    public Board getBoard(Long id) {
        Board board = boardDao.findById(id);
        if (board == null) {
            throw new ApiException(HttpStatus.NOT_FOUND);
        }
        return board;
    }

    public List<Board> getBoards() {
        return boardDao.findAll();
    }

    @Transactional
    public Board updateBoard(Long id, BoardRequest request) {
        Board board = boardDao.findById(id);
        if (board == null) {
            throw new ApiException(HttpStatus.NOT_FOUND);
        }

        board.update(request.getName());
        return board;
    }

    @Transactional
    public void deleteBoard(Long id) {
        Board board = boardDao.findById(id);
        if (board == null) {
            throw new ApiException(HttpStatus.NOT_FOUND);
        }

        if (articleDao.existsByBoardId(id)) {
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }

        boardDao.delete(board);
    }
}
