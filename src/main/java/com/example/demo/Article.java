package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Article {

    private Long id;

    @JsonProperty("author_id")
    private Long authorId;

    @JsonProperty("board_id")
    private Long boardId;

    private String title;
    private String content;

    @JsonProperty("created_date")
    private LocalDateTime createdTime;

    @JsonProperty("modified_date")
    private LocalDateTime updatedTime;

    public Article() {
    }

    public Article(Long boardId,
                   Long id,
                   Long authorId,
                   String title,
                   String content,
                   LocalDateTime createdTime,
                   LocalDateTime updatedTime) {
        this.boardId = boardId;
        this.id = id;
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Long getBoardId() {
        return boardId;
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
}
