package com.example.demo;

public class ViewResponse {
    private final String boardName;
    private final String title;
    private final String authorName;
    private final String createdTime;
    private final String content;

    public ViewResponse(String boardName,
                        String title,
                        String authorName,
                        String createdTime,
                        String content) {
        this.boardName = boardName;
        this.title = title;
        this.authorName = authorName;
        this.createdTime = createdTime;
        this.content = content;
    }

    public String getBoardName() {
        return boardName;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getContent() {
        return content;
    }
}
