package com.zack.article.Data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class ArticleCopy{
    @NonNull
    @PrimaryKey
    private String objectId;
    private String author;
    private String title;
    private String content;

    @NonNull
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(@NonNull String objectId) {
        this.objectId = objectId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor( String author) {
        this.author = author;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle( String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

