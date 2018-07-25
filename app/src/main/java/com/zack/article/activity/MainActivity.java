package com.zack.article.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zack.article.Anim.AnimTool;
import com.zack.article.bean.ArticleBean;
import com.zack.article.Data.DataUtils;
import com.zack.article.R;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TextView title;
    private TextView author;
    private TextView content;
    private DrawerLayout drawerLayout;

    private ImageView refresh,like,menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initLogic();
        reuqestData();
    }

    private void reuqestData() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                final ArticleBean articleBean = DataUtils.getArticleRandom();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        upDateView(articleBean);
                    }
                });
            }
        }.start();
    }

    private void upDateView(ArticleBean article) {
        AnimTool.stopRotate(refresh);
        title.setText(article.getTitle());
        author.setText(article.getAuthor());
        content.setText(article.getContent());

    }

    private void initLogic() {
        menu.setOnClickListener(this);
        refresh.setOnClickListener(this);
        like.setOnClickListener(this);
    }

    private void initView() {
        title = findViewById(R.id.title);
        author = findViewById(R.id.author);
        content = findViewById(R.id.content);
        drawerLayout = findViewById(R.id.drawer);
        refresh = findViewById(R.id.refresh);
        like = findViewById(R.id.like);
        menu = findViewById(R.id.menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu:
                drawerLayout.openDrawer(GravityCompat.END);
                break;
            case R.id.like:
                break;
            case R.id.refresh:
                reuqestData();
                showLoadingArticle();
                break;
        }
    }

    private void showLoadingArticle(){
        AnimTool.startRotate(refresh);
    }
}
