package com.example.demo.repository;

import com.example.demo.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByBoardIdOrderByIdDesc(Long boardId);

    boolean existsByMemberId(Long memberId);

    boolean existsByBoardId(Long boardId);

}
