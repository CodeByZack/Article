package com.zack.article.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.zack.article.R;

public class BaseActivity extends AppCompatActivity {
    protected ImmersionBar bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bar = ImmersionBar.with(this);
        setStatusBar();
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
}
