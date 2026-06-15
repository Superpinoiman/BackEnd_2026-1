package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ArticleRepository {
    private final Map<Integer, Article> articleMap = new HashMap<>();
    private int lastId = 0;

    public int nextId() {
        return ++lastId;
    }

    public Article save(Article article) {
        articleMap.put(article.getId(), article);
        return article;
    }

    public Optional<Article> findById(int id) {
        return Optional.ofNullable(articleMap.get(id));
    }

    public List<Article> findAll() {
        return new ArrayList<>(articleMap.values());
    }

    public List<Article> findByBoardId(int boardId) {
        return articleMap.values().stream()
                .filter(article -> article.getBoardId() == boardId)
                .toList();
    }

    public void delete(int id) {
        articleMap.remove(id);
    }

    public boolean existsByAuthorId(int authorId) {
        return articleMap.values().stream()
                .anyMatch(article -> article.getAuthorId() == authorId);
    }

    public boolean existsByBoardId(int boardId) {
        return articleMap.values().stream()
                .anyMatch(article -> article.getBoardId() == boardId);
    }
}
