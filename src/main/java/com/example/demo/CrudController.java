package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/article")
public class CrudController {
    private final Map<Integer, Article> articleMap = new HashMap<>();

    @PostMapping
    public ResponseEntity<Article> postArticle(@RequestBody ArticleRequest request) {
        Article article = new Article(++lastId, request.getTitle(), request.getContent());
        articleMap.put(article.getId(), article);

        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Article> getArticle(@PathVariable int id) {
        Article article = articleMap.get(id);

        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> putArticle(@PathVariable int id, @RequestBody ArticleRequest request) {
        Article article = articleMap.get(id);

        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        article = new Article(id, request.getTitle(), request.getContent());
        articleMap.put(id, article);

        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable int id) {
        Article article = articleMap.get(id);

        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        articleMap.remove(id);

        return ResponseEntity.ok(article);
    }
}
