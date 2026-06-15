package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CrudController {
    private final ArticleService articleService;
    private final MemberService memberService;
    private final BoardService boardService;

    public CrudController(ArticleService articleService,
                          MemberService memberService,
                          BoardService boardService) {
        this.articleService = articleService;
        this.memberService = memberService;
        this.boardService = boardService;
    }

    @PostMapping("/article")
    @ResponseBody
    public ResponseEntity<Article> postArticle(@Valid @RequestBody ArticleRequest request) {
        Article article = articleService.createArticle(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @GetMapping("/article/{id}")
    @ResponseBody
    public ResponseEntity<Article> getArticle(@PathVariable int id) {
        return ResponseEntity.ok(articleService.getArticle(id));
    }

    @PutMapping("/article/{id}")
    @ResponseBody
    public ResponseEntity<Article> putArticle(@PathVariable int id,
                                              @Valid @RequestBody ArticleRequest request) {
        return ResponseEntity.ok(articleService.updateArticle(id, request));
    }

    @DeleteMapping("/article/{id}")
    @ResponseBody
    public ResponseEntity<Article> deleteArticle(@PathVariable int id) {
        return ResponseEntity.ok(articleService.deleteArticle(id));
    }

    @GetMapping("/articles")
    @ResponseBody
    public List<Article> getArticles(@RequestParam int boardId) {
        return articleService.getArticlesByBoardId(boardId);
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        model.addAttribute("posts", articleService.getPosts());
        return "posts";
    }

    @GetMapping("/members")
    @ResponseBody
    public List<Member> getMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/members/{id}")
    @ResponseBody
    public ResponseEntity<Member> getMember(@PathVariable int id) {
        return ResponseEntity.ok(memberService.getMember(id));
    }

    @PostMapping("/members")
    @ResponseBody
    public ResponseEntity<Member> createMember(@Valid @RequestBody MemberRequest request) {
        Member member = memberService.createMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @PutMapping("/members/{id}")
    @ResponseBody
    public ResponseEntity<Member> updateMember(@PathVariable int id,
                                               @Valid @RequestBody MemberRequest request) {
        return ResponseEntity.ok(memberService.updateMember(id, request));
    }

    @DeleteMapping("/members/{id}")
    @ResponseBody
    public ResponseEntity<Member> deleteMember(@PathVariable int id) {
        return ResponseEntity.ok(memberService.deleteMember(id));
    }

    @GetMapping("/boards")
    @ResponseBody
    public List<Board> getBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/boards/{id}")
    @ResponseBody
    public ResponseEntity<Board> getBoard(@PathVariable int id) {
        return ResponseEntity.ok(boardService.getBoard(id));
    }

    @PostMapping("/boards")
    @ResponseBody
    public ResponseEntity<Board> createBoard(@Valid @RequestBody BoardRequest request) {
        Board board = boardService.createBoard(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(board);
    }

    @PutMapping("/boards/{id}")
    @ResponseBody
    public ResponseEntity<Board> updateBoard(@PathVariable int id,
                                             @Valid @RequestBody BoardRequest request) {
        return ResponseEntity.ok(boardService.updateBoard(id, request));
    }

    @DeleteMapping("/boards/{id}")
    @ResponseBody
    public ResponseEntity<Board> deleteBoard(@PathVariable int id) {
        return ResponseEntity.ok(boardService.deleteBoard(id));
    }
}
