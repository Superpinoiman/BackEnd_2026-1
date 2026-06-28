package com.example.demo.service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
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

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public ArticleService(ArticleRepository articleRepository,
                          MemberRepository memberRepository,
                          BoardRepository boardRepository) {
        this.articleRepository = articleRepository;
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Article createArticle(ArticleCreateRequest request) {
        Member member = memberRepository.findById(request.getAuthorId());
        if (member == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }

        Board board = boardRepository.findById(request.getBoardId());
        if (board == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }

        Article article = new Article(
                board,
                member,
                request.getTitle(),
                request.getContent()
        );

        board.addArticle(article);
        boardRepository.save(board);
        return article;
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

    public List<Article> getArticlesByBoardId(Long boardId) {
        return articleRepository.findByBoardId(boardId);
    }

    @Transactional
    public Article updateArticle(Long id, ArticleUpdateRequest request) {
        Article article = articleRepository.findById(id);
        if (article == null) {
            throw new ApiException(HttpStatus.NOT_FOUND);
        }

        Board board = boardRepository.findById(request.getBoardId());
        if (board == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }

        article.update(board, request.getTitle(), request.getContent());
        return article;
    }

    @Transactional
    public void deleteArticle(Long id) {
        Article article = articleRepository.findById(id);
        if (article == null) {
            throw new ApiException(HttpStatus.NOT_FOUND);
        }

        articleRepository.delete(article);
    }
}
