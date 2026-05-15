package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/article")
public class CrudController {

    private final Map<Integer, Article> articleMap = new HashMap<>();
    private int lastId = 0;

    @PostMapping
    public ResponseEntity<Article> postArticle(@RequestParam String title, @RequestParam String content) {
        Article article = new Article(++lastId, title, content);
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
    public ResponseEntity<Article> putArticle(@PathVariable int id, @RequestParam String title, @RequestParam String content) {
        Article article = articleMap.get(id);

        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        article = new Article(id, title, content);
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
