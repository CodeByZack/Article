package com.zack.article.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vise.log.ViseLog;
import com.zack.article.Anim.AnimTool;
import com.zack.article.bean.ArticleBean;
import com.zack.article.Data.DataUtils;
import com.zack.article.R;

import static android.view.View.FOCUS_UP;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TextView title;
    private TextView author;
    private TextView content;
    private TextView count;
    private DrawerLayout drawerLayout;
    private ImageView refresh,like,menu;
    private ScrollView scrollView;

    private boolean countFlag;
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
        countFlag = false;
        title.setText(article.getTitle());
        author.setText(article.getAuthor());
        content.setText(article.getContent());
        count.setText("全文完，共"+article.getContent().length()+"字");
        //scrollView.fullScroll(FOCUS_UP);
        scrollView.scrollTo(scrollView.getScrollX(),0);
    }

    private void initLogic() {
        menu.setOnClickListener(this);
        refresh.setOnClickListener(this);
        like.setOnClickListener(this);
        scrollView.setOnTouchListener(new TouchListenerImpl());
    }

    private void initView() {
        title = findViewById(R.id.title);
        author = findViewById(R.id.author);
        content = findViewById(R.id.content);
        drawerLayout = findViewById(R.id.drawer);
        refresh = findViewById(R.id.refresh);
        like = findViewById(R.id.like);
        menu = findViewById(R.id.menu);
        count = findViewById(R.id.count);

        scrollView = findViewById(R.id.container);
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
    /**
     * Demo描述:
     * 监听ScrollView滑动到顶端和底部
     *
     * 注意事项:
     * 1 mScrollView.getChildAt(0).getMeasuredHeight()表示:
     *   ScrollView所占的高度.即ScrollView内容的高度.常常有一
     *   部分内容要滑动后才可见,这部分的高度也包含在了
     *   mScrollView.getChildAt(0).getMeasuredHeight()中
     *
     * 2 view.getScrollY表示:
     *   ScrollView顶端已经滑出去的高度
     *
     * 3 view.getHeight()表示:
     *   ScrollView的可见高度
     *
     */
    private class TouchListenerImpl implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    break;
                case MotionEvent.ACTION_MOVE:
//                    int scrollY=view.getScrollY();
//                    int height=view.getHeight();
//                    int scrollViewMeasuredHeight=scrollView.getChildAt(0).getMeasuredHeight();
                    Rect localRect = new Rect();
                    boolean localVisibility = count.getLocalVisibleRect(localRect);
                    if(!countFlag && localVisibility){
                        ViseLog.d("滑动到底部事件！"+localVisibility);
                        countFlag = true;
                    }
                    break;

                default:
                    break;
            }
            return false;
        }

    };
}
