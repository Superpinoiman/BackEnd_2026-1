package com.example.demo;

import java.time.LocalDateTime;

public class Article {
    private final int boardId;
    private final int id;
    private final int authorId;
    private final String title;
    private final String content;
    private final LocalDateTime createdTime;
    private final LocalDateTime updatedTime;

    public Article(int boardId,
                   int id,
                   int authorId,
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

    public int getBoardId() {
        return boardId;
    }

    public int getId() {
        return id;
    }

    public int getAuthorId() {
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
}

