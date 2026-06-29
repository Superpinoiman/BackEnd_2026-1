package com.example.demo.service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.BoardRepository;
import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import com.example.demo.dto.ArticleUpdateRequest;
import com.example.demo.exception.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    public ArticleService(ArticleRepository articleRepository,
                          BoardRepository boardRepository) {
        this.articleRepository = articleRepository;
        this.boardRepository = boardRepository;
    }

    public Article getArticle(Long id) {
        Article article = articleRepository.findById(id);
        if (article == null) {
            throw new ApiException(HttpStatus.NOT_FOUND);
        }
        return article;
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Transactional
    public Article updateArticle(Long id, ArticleUpdateRequest request) {
        Article article = articleRepository.findById(id);
        if (article == null) {
            throw new ApiException(HttpStatus.NOT_FOUND);
        }

        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST));

        article.update(board, request.getTitle(), request.getContent());
        return article;
    }
}
