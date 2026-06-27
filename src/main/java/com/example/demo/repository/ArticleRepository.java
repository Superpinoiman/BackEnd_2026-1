package com.example.demo.repository;

import com.example.demo.domain.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleRepository {

    @PersistenceContext
    private EntityManager em;

    public Article save(Article article) {
        em.persist(article);
        return article;
    }

    public Article findById(Long id) {
        return em.find(Article.class, id);
    }

    public List<Article> findAll() {
        return em.createQuery("select a from Article a order by a.id desc", Article.class)
                .getResultList();
    }

    public List<Article> findByBoardId(Long boardId) {
        return em.createQuery(
                        "select a from Article a where a.boardId = :boardId order by a.id desc",
                        Article.class)
                .setParameter("boardId", boardId)
                .getResultList();
    }

    public boolean existsByAuthorId(Long authorId) {
        Long count = em.createQuery(
                        "select count(a) from Article a where a.authorId = :authorId",
                        Long.class)
                .setParameter("authorId", authorId)
                .getSingleResult();
        return count > 0;
    }

    public boolean existsByBoardId(Long boardId) {
        Long count = em.createQuery(
                        "select count(a) from Article a where a.boardId = :boardId",
                        Long.class)
                .setParameter("boardId", boardId)
                .getSingleResult();
        return count > 0;
    }

    public void delete(Article article) {
        em.remove(article);
    }
}
