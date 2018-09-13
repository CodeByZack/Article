package com.zack.article.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.umeng.analytics.MobclickAgent;
import com.zack.article.R;

public class BaseActivity extends AppCompatActivity {
    protected ImmersionBar bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bar = ImmersionBar.with(this);
        setStatusBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private void setStatusBar() {
        bar.statusBarColor(R.color.bg_light).fitsSystemWindows(true).statusBarDarkFont(true,0.2f).init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bar!=null){
            bar.destroy();
        }
    }

    protected void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
