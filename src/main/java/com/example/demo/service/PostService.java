package com.example.demo.service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.BoardDao;
import com.example.demo.dao.MemberDao;
import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.example.demo.dto.PostResponse;
import com.example.demo.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final ArticleDao articleDao;
    private final MemberDao memberDao;
    private final BoardDao boardDao;

    public PostService(ArticleDao articleDao, MemberDao memberDao, BoardDao boardDao) {
        this.articleDao = articleDao;
        this.memberDao = memberDao;
        this.boardDao = boardDao;
    }

    public List<PostResponse> getPosts(Long boardId) {
        Board board = boardDao.findById(boardId);
        if (board == null) {
            throw new ApiException(HttpStatus.NOT_FOUND);
        }

        List<Article> articles = articleDao.findByBoardId(boardId);
        List<PostResponse> result = new ArrayList<>();

        for (Article article : articles) {
            Member member = memberDao.findById(article.getAuthorId());
            String authorName = (member != null) ? member.getName() : "알 수 없음";

            result.add(new PostResponse(
                    article.getId(),
                    article.getTitle(),
                    article.getContent(),
                    authorName,
                    board.getName(),
                    article.getCreatedDate()
            ));
        }

        return result;
    }
}
