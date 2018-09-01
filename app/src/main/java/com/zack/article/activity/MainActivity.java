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
import android.widget.Toast;

import com.vise.log.ViseLog;
import com.zack.article.Anim.AnimTool;
import com.zack.article.bean.Articles;
import com.zack.article.Data.DataUtils;
import com.zack.article.R;

import static android.view.View.FOCUS_UP;
import java.io.IOException;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TextView title;
    private TextView author;
    private TextView content;
    private TextView count;
    private ScrollView scrollView;
    private DrawerLayout drawerLayout;
    private ImageView refresh,like,menu;

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
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                final Articles articleBean = DataUtils.getArticleRandom();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        upDateView(articleBean);
//                    }
//                });
//            }
//        }.start();

        DataUtils.getTodayArticle(new FindListener<Articles>() {
            @Override
            public void done(List<Articles> list, BmobException e) {
                if(e == null){
                    upDateView(list.get(0));
                }else{
                    ViseLog.d(e);
                }
            }
        });
    }

    private void reuqestRandomData() {
        DataUtils.getRandomArticle(new FindListener<Articles>() {
            @Override
            public void done(List<Articles> list, BmobException e) {
                if(e == null){
                    upDateView(list.get(0));
                }else{
                    ViseLog.d(e);
                }
            }
        });
    }

    private void upDateView(Articles article) {
        Toast.makeText(this, article.getTitle(), Toast.LENGTH_SHORT).show();
        AnimTool.stopRotate(refresh);
        countFlag = false;
        title.setText(article.getTitle());
        author.setText(article.getAuthor());
        content.setText(article.getContent());
        count.setText("全文完，共"+article.getContent().length()+"字");
        //scrollView.fullScroll(FOCUS_UP);
        // scrollView.scrollTo(scrollView.getScrollX(),0);
        scrollView.scrollTo(0, 0);
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
        scrollView = findViewById(R.id.scrollView);
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
                reuqestRandomData();
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
