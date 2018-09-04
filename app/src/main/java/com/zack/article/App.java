package com.zack.article;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.vise.log.ViseLog;
import com.vise.log.inner.LogcatTree;
import com.zack.article.Util.ThemeConfig;

import cn.bmob.v3.Bmob;

public class App extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initLog();
        ThemeConfig.init(this);
        Bmob.initialize(this, "818f8d7565f7588990922e937dd3e7c8");
    }

    private void initLog() {
        ViseLog.getLogConfig()
                .configAllowLog(true)//是否输出日志
                .configShowBorders(true)//是否排版显示
                .configTagPrefix("ViseLog")//设置标签前缀
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}")//个性化设置标签，默认显示包名
                .configLevel(Log.VERBOSE);//设置日志最小输出级别，默认Log.VERBOSE
        ViseLog.plant(new LogcatTree());//添加打印日志信息到Logcat的树
    }

    public static Context getContext() {
        return context;
    }
}
