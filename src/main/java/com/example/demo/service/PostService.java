package com.example.demo.service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
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

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public PostService(ArticleRepository articleRepository, MemberRepository memberRepository, BoardRepository boardRepository) {
        this.articleRepository = articleRepository;
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
    }

    public List<PostResponse> getPosts(Long boardId) {
        Board board = boardRepository.findById(boardId);
        if (board == null) {
            throw new ApiException(HttpStatus.NOT_FOUND);
        }

        List<Article> articles = articleRepository.findByBoardId(boardId);
        List<PostResponse> result = new ArrayList<>();

        for (Article article : articles) {
            Member member = memberRepository.findById(article.getAuthor().getId());
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
