package com.example.demo.controller;

import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.example.demo.dto.ArticleCreateRequest;
import com.example.demo.dto.ArticleUpdateRequest;
import com.example.demo.dto.BoardRequest;
import com.example.demo.dto.MemberRequest;
import com.example.demo.service.ArticleService;
import com.example.demo.service.BoardService;
import com.example.demo.service.MemberService;
import com.example.demo.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class CrudController {

    private final MemberService memberService;
    private final BoardService boardService;
    private final ArticleService articleService;
    private final PostService postService;

    public CrudController(MemberService memberService,
                          BoardService boardService,
                          ArticleService articleService,
                          PostService postService) {
        this.memberService = memberService;
        this.boardService = boardService;
        this.articleService = articleService;
        this.postService = postService;
    }

    @PostMapping("/members")
    @ResponseBody
    public ResponseEntity<Member> createMember(@Valid @RequestBody MemberRequest request) {
        Member member = memberService.createMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @GetMapping("/members/{id}")
    @ResponseBody
    public ResponseEntity<Member> getMember(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMember(id));
    }

    @GetMapping("/members")
    @ResponseBody
    public ResponseEntity<List<Member>> getMembers() {
        return ResponseEntity.ok(memberService.getMembers());
    }

    @PutMapping("/members/{id}")
    @ResponseBody
    public ResponseEntity<Member> updateMember(@PathVariable Long id,
                                               @Valid @RequestBody MemberRequest request) {
        return ResponseEntity.ok(memberService.updateMember(id, request));
    }

    @DeleteMapping("/members/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/boards")
    @ResponseBody
    public ResponseEntity<Board> createBoard(@Valid @RequestBody BoardRequest request) {
        Board board = boardService.createBoard(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(board);
    }

    @GetMapping("/boards/{id}")
    @ResponseBody
    public ResponseEntity<Board> getBoard(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoard(id));
    }

    @GetMapping("/boards")
    @ResponseBody
    public ResponseEntity<List<Board>> getBoards() {
        return ResponseEntity.ok(boardService.getBoards());
    }

    @PutMapping("/boards/{id}")
    @ResponseBody
    public ResponseEntity<Board> updateBoard(@PathVariable Long id,
                                             @Valid @RequestBody BoardRequest request) {
        return ResponseEntity.ok(boardService.updateBoard(id, request));
    }

    @DeleteMapping("/boards/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/articles")
    @ResponseBody
    public ResponseEntity<Article> createArticle(@Valid @RequestBody ArticleCreateRequest request) {
        Article article = articleService.createArticle(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @GetMapping("/articles/{id}")
    @ResponseBody
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticle(id));
    }

    @GetMapping("/articles")
    @ResponseBody
    public ResponseEntity<List<Article>> getArticles() {
        return ResponseEntity.ok(articleService.getArticles());
    }

    @PutMapping("/articles/{id}")
    @ResponseBody
    public ResponseEntity<Article> updateArticle(@PathVariable Long id,
                                                 @Valid @RequestBody ArticleUpdateRequest request) {
        return ResponseEntity.ok(articleService.updateArticle(id, request));
    }

    @DeleteMapping("/articles/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/posts")
    public String getPosts(@RequestParam Long boardId, Model model) {
        model.addAttribute("posts", postService.getPosts(boardId));
        return "posts";
    }
}
