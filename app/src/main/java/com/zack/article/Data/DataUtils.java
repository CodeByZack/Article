package com.zack.article.Data;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;

import com.vise.log.ViseLog;
import com.zack.article.bean.Articles;
import com.zack.article.util.SPUtil;
import com.zack.article.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Zackv on 2017/10/8.
 */

public class DataUtils {
    public static final String ReadedNum = "ReadedNum";

    public static void updateReadNum(int num, Context context){
        int oldNum = (int) SPUtil.getData(context,ReadedNum,0);
        int newNum = oldNum + num;
        SPUtil.saveDate(context,ReadedNum,newNum);
    }
    public static int getRededNum(Context context){
        return (int) SPUtil.getData(context,ReadedNum,0);
    }
    public static void getTodayArticle(FindListener listener){
        BmobQuery<Articles> query = new BmobQuery<Articles>();
        query.setLimit(1);
        query.findObjects(listener);
    }

    public static void getRandomArticle(final FindListener listener){
        final BmobQuery<Articles> query = new BmobQuery<Articles>();
        query.count(Articles.class, new CountListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                ViseLog.d(integer);
                int skip = utils.randomNum(0,integer);
                query.setSkip(skip);
                query.findObjects(listener);
            }
        });
    }

}
