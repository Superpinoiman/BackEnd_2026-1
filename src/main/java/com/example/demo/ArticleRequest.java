package com.example.demo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ArticleRequest {

    @NotNull
    private Integer authorId;

    @NotNull
    private Integer boardId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public Integer getAuthorId() {
        return authorId;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
