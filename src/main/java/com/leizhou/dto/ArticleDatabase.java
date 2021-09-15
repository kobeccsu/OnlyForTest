package com.leizhou.dto;

public class ArticleDatabase {
    private ArticleListener articleListener;
    private User1 user;

    public void addListener(ArticleListener articleListener) {
        this.articleListener = articleListener;
    }

    public void setUser(User1 user) {
        this.user = user;
    }
}
