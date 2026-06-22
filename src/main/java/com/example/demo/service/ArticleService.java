package com.example.demo.service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.BoardDao;
import com.example.demo.dao.MemberDao;
import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.example.demo.dto.ArticleCreateRequest;
import com.example.demo.dto.ArticleUpdateRequest;
import com.example.demo.exception.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleDao articleDao;
    private final MemberDao memberDao;
    private final BoardDao boardDao;

    public ArticleService(ArticleDao articleDao, MemberDao memberDao, BoardDao boardDao) {
        this.articleDao = articleDao;
        this.memberDao = memberDao;
        this.boardDao = boardDao;
    }

    @Transactional
    public Article createArticle(ArticleCreateRequest request) {
        Member member = memberDao.findById(request.getAuthorId());
        if (member == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }

        Board board = boardDao.findById(request.getBoardId());
        if (board == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }

        Article article = new Article(
                request.getBoardId(),
                request.getAuthorId(),
                request.getTitle(),
                request.getContent()
        );

        return articleDao.save(article);
    }

    public Article getArticle(Long id) {
        Article article = articleDao.findById(id);
        if (article == null) {
            throw new ApiException(HttpStatus.NOT_FOUND);
        }
        return article;
    }

    public List<Article> getArticles() {
        return articleDao.findAll();
    }

    public List<Article> getArticlesByBoardId(Long boardId) {
        return articleDao.findByBoardId(boardId);
    }

    @Transactional
    public Article updateArticle(Long id, ArticleUpdateRequest request) {
        Article article = articleDao.findById(id);
        if (article == null) {
            throw new ApiException(HttpStatus.NOT_FOUND);
        }

        Board board = boardDao.findById(request.getBoardId());
        if (board == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }

        article.update(request.getBoardId(), request.getTitle(), request.getContent());
        return article;
    }

    @Transactional
    public void deleteArticle(Long id) {
        Article article = articleDao.findById(id);
        if (article == null) {
            throw new ApiException(HttpStatus.NOT_FOUND);
        }

        articleDao.delete(article);
    }
}
