package com.zack.article.Data;

import android.content.Context;

import com.vise.log.ViseLog;
import com.zack.article.Bean.Articles;
import com.zack.article.Util.SPUtil;
import com.zack.article.utils;

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
        query.order("-updatedAt");
        query.setLimit(1);
        query.findObjects(listener);
    }

    public static void getRandomArticle(final FindListener listener){
        final BmobQuery<Articles> query = new BmobQuery<Articles>();
        query.count(Articles.class, new CountListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                int skip = utils.randomNum(0,integer);
                query.setSkip(skip);
                query.findObjects(listener);
            }
        });
    }

}
