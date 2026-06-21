package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ArticleCreateRequest {

    @NotNull
    @JsonProperty("board_id")
    private Long boardId;

    @NotNull
    @JsonProperty("author_id")
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

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
