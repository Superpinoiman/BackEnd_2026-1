package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ArticleRepository {
    private final Map<Integer, Article> articleMap = new HashMap<>();
    private int lastId = 0;

    public Article save(Article article) {
        articleMap.put(article.getId(), article);
        return article;
    }

    public int nextId() {
        return ++lastId;
    }

    public Optional<Article> findById(int id) {
        return Optional.ofNullable(articleMap.get(id));
    }

    public List<Article> findAll() {
        return new ArrayList<>(articleMap.values());
    }

    public void delete(int id) {
        articleMap.remove(id);
    }
}

