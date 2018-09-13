package com.zack.article.Data;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.vise.log.ViseLog;
import com.zack.article.Bean.Articles;
import com.zack.article.Bean.UpdateInfo;
import com.zack.article.Bean.comments;
import com.zack.article.Util.NetUtils;
import com.zack.article.Util.SPUtil;
import com.zack.article.Util.Utils;


import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Zackv on 2017/10/8.
 */

public class DataUtils {
    public static final String ReadedNum = "ReadedNum";
    public static final String ReadedArticleCount = "ReadedArticleCount";

    public static void updateReadNum(int num, Context context){
        int oldNum = (int) SPUtil.getData(context,ReadedNum,0);
        int newNum = oldNum + num;
        SPUtil.saveDate(context,ReadedNum,newNum);
    }
    public static int getRededNum(Context context){
        return (int) SPUtil.getData(context,ReadedNum,0);
    }

    public static void updateReadArticleCount(int num, Context context){
        int oldNum = (int) SPUtil.getData(context,ReadedArticleCount,0);
        int newNum = oldNum + num;
        SPUtil.saveDate(context,ReadedArticleCount,newNum);
    }
    public static int getRededArticleCount(Context context){
        return (int) SPUtil.getData(context,ReadedArticleCount,0);
    }
    public static void getTodayArticle(FindListener listener){
        BmobQuery<Articles> query = new BmobQuery<Articles>();
        query.order("-createdAt");
        query.setLimit(1);
        query.findObjects(listener);
    }

    public static void getRandomArticle(final FindListener listener){
        final BmobQuery<Articles> query = new BmobQuery<Articles>();
        query.count(Articles.class, new CountListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                int skip = Utils.randomNum(0,integer);
                query.setSkip(skip);
                query.findObjects(listener);
            }
        });
    }

    public static void getCommentsByArticleId(String articleId,FindListener listener){
        BmobQuery<comments> query = new BmobQuery<comments>();
        query.order("-updatedAt");
        query.addWhereEqualTo("articleId",articleId);
        query.findObjects(listener);
    }

    public static void submitComment(comments c, SaveListener listener){
        c.save(listener);
    }

    public static void getUpdateInfo(final UpdateCallBack callBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String res = NetUtils.get("https://apks-1252514056.cos.ap-chengdu.myqcloud.com/article_update.json");
                ViseLog.d(res);
                JSONObject json = null;
                try {
                    json = new JSONObject(res);
                    final UpdateInfo updateInfo = new UpdateInfo();
                    String name = json.getString("app_name");
                    String updateinfo = json.getString("app_updateInfo");
                    String download_url = json.getString("download_url");
                    int version = json.getInt("app_version");

                    if(!TextUtils.isEmpty(name)){
                        updateInfo.setApp_name(name);
                    }
                    if(!TextUtils.isEmpty(updateinfo)){
                        updateInfo.setApp_updateInfo(updateinfo);
                    }
                    if(!TextUtils.isEmpty(download_url)){
                        updateInfo.setDownload_url(download_url);
                    }
                    updateInfo.setApp_version(version);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onResponse(updateInfo);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
