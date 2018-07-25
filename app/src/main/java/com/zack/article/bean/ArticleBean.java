package com.zack.article.bean;

/**
 * Created by Zackv on 2017/10/8.
 */

public class ArticleBean {
    private String author;
    private String time;
    private String id;
    private String title;
    private String content;

    public ArticleBean(String author, String title, String content) {
        this.author = author;
        this.content = content;
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
