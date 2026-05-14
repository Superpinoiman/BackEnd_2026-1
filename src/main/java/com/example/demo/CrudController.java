package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/article")
public class CrudController {

    private class Article {
        private int id;
        private String title;
        private String content;

        public Article(int id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }

        public int getId() {
            return id;
        }
        public String getTitle() {
            return title;
        }
        public String getContent() {
            return content;
        }
    }

    private Map<Integer, Article> articleMap = new HashMap<>();
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
}
