package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ArticleCreateRequest {

    @NotNull
    private Long boardId;

    @NotNull
    private Long authorId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public Long getBoardId() {
        return boardId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
