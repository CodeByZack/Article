package com.zack.article.Event;

import com.zack.article.Bean.Articles;
import com.zack.article.Data.ArticleCopy;

public class OpenCollectArticleEvent {
    public ArticleCopy articleCopy;

    public OpenCollectArticleEvent(ArticleCopy articleCopy) {
        this.articleCopy = articleCopy;
    }
}
