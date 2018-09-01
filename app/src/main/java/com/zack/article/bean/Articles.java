package com.zack.article.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Zackv on 2017/10/8.
 */

public class Articles extends BmobObject{
    private String author;
    private String title;
    private String content;

    public Articles(String author, String title, String content) {
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
