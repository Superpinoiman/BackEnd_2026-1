package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleDao articleDao;
    private final MemberDao memberDao;
    private final BoardDao boardDao;

    public ArticleService(ArticleDao articleDao,
                          MemberDao memberDao,
                          BoardDao boardDao) {
        this.articleDao = articleDao;
        this.memberDao = memberDao;
        this.boardDao = boardDao;
    }

    @Transactional
    public Article createArticle(ArticleCreateRequest request) {
        memberDao.findById(request.getAuthorId())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST));

        boardDao.findById(request.getBoardId())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST));

        Long id = articleDao.insert(request);

        return articleDao.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));
    }

    public Article getArticle(Long id) {
        return articleDao.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));
    }

    public List<Article> getAllArticles() {
        return articleDao.findAll();
    }

    public List<Article> getArticlesByBoardId(Long boardId) {
        boardDao.findById(boardId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));

        return articleDao.findByBoardId(boardId);
    }

    @Transactional
    public Article updateArticle(Long id, ArticleUpdateRequest request) {
        articleDao.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));

        boardDao.findById(request.getBoardId())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST));

        articleDao.update(id, request);

        return articleDao.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Article deleteArticle(Long id) {
        Article article = articleDao.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));

        articleDao.deleteById(id);
        return article;
    }

    public List<ViewResponse> getPosts(Long boardId) {
        boardDao.findById(boardId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return articleDao.findByBoardId(boardId).stream()
                .map(article -> {
                    Member member = memberDao.findById(article.getAuthorId()).orElse(null);
                    Board board = boardDao.findById(article.getBoardId()).orElse(null);

                    String authorName = member != null ? member.getName() : "유령 회원";
                    String boardName = board != null ? board.getName() : "유령 게시판";

                    return new ViewResponse(
                            boardName,
                            article.getTitle(),
                            authorName,
                            article.getCreatedTime().format(formatter),
                            article.getContent()
                    );
                })
                .toList();
    }
}
