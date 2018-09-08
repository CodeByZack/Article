package com.zack.article.Activity;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.vise.log.ViseLog;
import com.zack.article.Anim.AnimTool;
import com.zack.article.Data.ArticleCopy;
import com.zack.article.Data.DataBase;
import com.zack.article.Bean.Articles;
import com.zack.article.Data.DataUtils;
import com.zack.article.Event.OpenCollectArticleEvent;
import com.zack.article.R;
import com.zack.article.Util.ThemeConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TextView title;
    private TextView author;
    private TextView content;
    private ScrollView scrollView;
    private TextView count;
    private TextView readedNum;
    private DrawerLayout drawerLayout;
    private ImageView refresh,like,menu;
    private LinearLayout llCollect,llSetting,llAbout;
    private LinearLayout mainContainer;

    private boolean countFlag;
    private int nowArticleNum;
    private Articles articles;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        applyTheme();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OpenCollectArticleEvent event) {
        Articles articles = new Articles();
        articles.setAuthor(event.articleCopy.getAuthor());
        articles.setTitle(event.articleCopy.getTitle());
        articles.setContent(event.articleCopy.getContent());
        articles.setObjectId(event.articleCopy.getObjectId());
        upDateView(articles);
    }

    private void applyTheme(){
        title.setTextColor(getResources().getColor(ThemeConfig.getTitleColor()));
        author.setTextColor(getResources().getColor(ThemeConfig.getAuthorColor()));
        content.setTextColor(getResources().getColor(ThemeConfig.getContentColor()));

        title.setTextSize(ThemeConfig.getTitleSize());
        author.setTextSize(ThemeConfig.getAuthorSize());
        content.setTextSize(ThemeConfig.getContentSize());

        count.setTextColor(getResources().getColor(ThemeConfig.getContentColor()));
        mainContainer.setBackgroundColor(getResources().getColor(ThemeConfig.getBgColor()));
        bar.statusBarColor(ThemeConfig.getBgColor()).init();
        int colors[] = { ThemeConfig.getLeftStartColor(),ThemeConfig.getLeftEndColor()};
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
        readedNum.setBackground(bg);
        readedNum.setTextColor(getResources().getColor(ThemeConfig.getContentColor()));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initLogic();
        reuqestData();
        EventBus.getDefault().register(this);
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
        this.articles = article;
        Toast.makeText(this, article.getTitle(), Toast.LENGTH_SHORT).show();
        AnimTool.stopRotate(refresh);
        countFlag = false;
        title.setText(article.getTitle());
        author.setText(article.getAuthor());
        content.setText(article.getContent());
        nowArticleNum = article.getContent().length();
        count.setText("全文完，共"+nowArticleNum+"字");
        scrollView.scrollTo(0, 0);
        checkCollect();
    }

    private void checkCollect() {
        List<ArticleCopy> temp = DataBase.getInstance().articlesDao().getCollectById(articles.getObjectId());
        Drawable likeDrawable = getResources().getDrawable(R.drawable.like_solid);
        Drawable unlikeDrawable = getResources().getDrawable(R.drawable.like);
        if(temp.size()>0){
            like.setImageDrawable(likeDrawable);
        }else{
            like.setImageDrawable(unlikeDrawable);
        }
    }

    private void initLogic() {
        menu.setOnClickListener(this);
        refresh.setOnClickListener(this);
        like.setOnClickListener(this);
        llSetting.setOnClickListener(this);
        llAbout.setOnClickListener(this);
        llCollect.setOnClickListener(this);
        scrollView.setOnTouchListener(new TouchListenerImpl());
    }

    private void initView() {
        title = findViewById(R.id.title);
        author = findViewById(R.id.author);
        readedNum = findViewById(R.id.tv_readedNum);
        content = findViewById(R.id.content);
        drawerLayout = findViewById(R.id.drawer);
        refresh = findViewById(R.id.refresh);
        like = findViewById(R.id.like);
        menu = findViewById(R.id.menu);
        count = findViewById(R.id.count);
        scrollView = findViewById(R.id.scrollView);
        readedNum.setText("你已阅读"+DataUtils.getRededNum(MainActivity.this)+"字");
        llAbout = findViewById(R.id.ll_about);
        llCollect = findViewById(R.id.ll_collect);
        llSetting = findViewById(R.id.ll_setting);
        mainContainer = findViewById(R.id.main_container);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu:
                drawerLayout.openDrawer(GravityCompat.END);
                break;
            case R.id.like:
                AnimTool.startShake(like);
                ArticleCopy article = new ArticleCopy();
                article.setObjectId(articles.getObjectId());
                article.setAuthor(articles.getAuthor());
                article.setContent(articles.getContent());
                article.setTitle(articles.getTitle());
                DataBase.getInstance().articlesDao().insert(article);
                toast("收藏成功！");
                checkCollect();
                break;
            case R.id.refresh:
                reuqestRandomData();
                showLoadingArticle();
                break;
            case R.id.ll_about:
                startActivity(new Intent(this,AboutActivity.class));
                drawerLayout.closeDrawer(GravityCompat.END);
                break;
            case R.id.ll_collect:
                startActivity(new Intent(this,CollectActivity.class));
                drawerLayout.closeDrawer(GravityCompat.END);
                break;
            case R.id.ll_setting:
                startActivity(new Intent(this,SettingActivity.class));
                drawerLayout.closeDrawer(GravityCompat.END);
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
                    Rect localRect = new Rect();
                    boolean localVisibility = count.getLocalVisibleRect(localRect);

                    if(!countFlag && localVisibility){
                        ViseLog.d("滑动到底部事件！"+localVisibility);
                        countFlag = true;
                        DataUtils.updateReadNum(nowArticleNum,MainActivity.this);
                        readedNum.setText("你已阅读"+DataUtils.getRededNum(MainActivity.this)+"字");
                    }
                    break;

                default:
                    break;
            }
            return false;
        }

    };
}
