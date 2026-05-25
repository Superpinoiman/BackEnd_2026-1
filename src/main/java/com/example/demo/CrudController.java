package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class CrudController {
    private final ArticleService articleService;

    public CrudController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/article")
    @ResponseBody
    public ResponseEntity<Article> postArticle(@RequestBody ArticleRequest request) {
        Article article = articleService.createArticle(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @GetMapping("/article/{id}")
    @ResponseBody
    public ResponseEntity<Article> getArticle(@PathVariable int id) {
        Article article = articleService.getArticle(id);

        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(article);
    }

    @PutMapping("/article/{id}")
    @ResponseBody
    public ResponseEntity<Article> putArticle(@PathVariable int id, @RequestBody ArticleRequest request) {
        Article article = articleService.updateArticle(id, request);

        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/article/{id}")
    @ResponseBody
    public ResponseEntity<Article> deleteArticle(@PathVariable int id) {
        Article article = articleService.deleteArticle(id);

        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(article);
    }

    @GetMapping("/articles")
    @ResponseBody
    public List<Article> getArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        model.addAttribute("posts", articleService.getPosts());
        return "posts";
    }
}
