package com.example.demo;

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

    @PostMapping("/articles")
    @ResponseBody
    public ResponseEntity<Article> postArticle(@RequestBody ArticleRequest request) {
        Article article = articleService.createArticle(request);

        if (article == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @GetMapping("/articles/{id}")
    @ResponseBody
    public ResponseEntity<Article> getArticle(@PathVariable int id) {
        Article article = articleService.getArticle(id);

        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(article);
    }

    @PutMapping("/articles/{id}")
    @ResponseBody
    public ResponseEntity<Article> putArticle(@PathVariable int id,
                                              @RequestBody ArticleRequest request) {
        Article article = articleService.updateArticle(id, request);

        if (article == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/articles/{id}")
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
    public List<Article> getArticles(@RequestParam(required = false) Integer boardId) {
        if (boardId == null) {
            return articleService.getAllArticles();
        }
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
        Member member = memberService.getMember(id);

        if (member == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(member);
    }

    @PostMapping("/members")
    @ResponseBody
    public ResponseEntity<Member> createMember(@RequestBody MemberRequest request) {
        Member member = memberService.createMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @PutMapping("/members/{id}")
    @ResponseBody
    public ResponseEntity<Member> updateMember(@PathVariable int id,
                                               @RequestBody MemberRequest request) {
        Member member = memberService.updateMember(id, request);

        if (member == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(member);
    }

    @DeleteMapping("/members/{id}")
    @ResponseBody
    public ResponseEntity<Member> deleteMember(@PathVariable int id) {
        Member member = memberService.deleteMember(id);

        if (member == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(member);
    }

    @GetMapping("/boards")
    @ResponseBody
    public List<Board> getBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/boards/{id}")
    @ResponseBody
    public ResponseEntity<Board> getBoard(@PathVariable int id) {
        Board board = boardService.getBoard(id);

        if (board == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(board);
    }

    @PostMapping("/boards")
    @ResponseBody
    public ResponseEntity<Board> createBoard(@RequestBody BoardRequest request) {
        Board board = boardService.createBoard(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(board);
    }

    @PutMapping("/boards/{id}")
    @ResponseBody
    public ResponseEntity<Board> updateBoard(@PathVariable int id,
                                             @RequestBody BoardRequest request) {
        Board board = boardService.updateBoard(id, request);

        if (board == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(board);
    }

    @DeleteMapping("/boards/{id}")
    @ResponseBody
    public ResponseEntity<Board> deleteBoard(@PathVariable int id) {
        Board board = boardService.deleteBoard(id);

        if (board == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(board);
    }
}
