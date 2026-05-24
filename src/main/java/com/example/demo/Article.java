package com.example.demo;

import java.time.LocalDateTime;

public class Article {
    private final int id;
    private final int authorId;
    private final int boardId;
    private final String title;
    private final String content;
    private final LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public Article(int id, int authorId, int boardId, String title, String content, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.id = id;
        this.authorId = authorId;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public int getId() {
        return id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getBoardId() {
        return boardId;
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
}

