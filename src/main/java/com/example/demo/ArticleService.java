package com.example.demo;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
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

    public Article createArticle(ArticleRequest request) {
        Member member = memberRepository.findById(request.getAuthorId()).orElse(null);

        if (member == null) {
            return null;
        }

        Board board = boardRepository.getDefaultBoard();

        if (board == null) {
            return null;
        }

        int id = articleRepository.nextId();

        Article article = new Article(
                board.getId(),
                id,
                member.getId(),
                request.getTitle(),
                request.getContent(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        return articleRepository.save(article);
    }

    public Article getArticle(int id) {
        return articleRepository.findById(id).orElse(null);
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article updateArticle(int id, ArticleRequest request) {
        Article oldArticle = articleRepository.findById(id).orElse(null);

        if (oldArticle == null) {
            return null;
        }

        Article updatedArticle = new Article(
                oldArticle.getBoardId(),
                id,
                oldArticle.getAuthorId(),
                request.getTitle(),
                request.getContent(),
                oldArticle.getCreatedTime(),
                LocalDateTime.now()
        );

        return articleRepository.save(updatedArticle);
    }

    public Article deleteArticle(int id) {
        Article article = articleRepository.findById(id).orElse(null);

        if (article == null) {
            return null;
        }

        articleRepository.delete(id);
        return article;
    }
}
