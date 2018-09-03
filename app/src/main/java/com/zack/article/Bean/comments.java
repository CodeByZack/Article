package com.zack.article.Bean;

import cn.bmob.v3.BmobObject;

public class comments extends BmobObject {
    private String content;
    private Articles articleId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Articles getArticleId() {
        return articleId;
    }

    public void setArticleId(Articles articleId) {
        this.articleId = articleId;
    }
}
