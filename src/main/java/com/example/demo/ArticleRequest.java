package com.example.demo;

public class ArticleRequest {
    private int authorId;
    private int boardId;
    private String title;
    private String content;

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

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
