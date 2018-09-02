package com.zack.article.activity;

import android.arch.persistence.room.Database;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zack.article.Data.ArticleCopy;
import com.zack.article.Data.DataBase;
import com.zack.article.R;
import com.zack.article.Data.ArticleCopy;

import java.util.LinkedList;
import java.util.List;


public class CollectActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private CollectAdapter collectAdapter;
    private List<ArticleCopy> data = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initView();
        initLogic();
    }

    private void initLogic() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collectAdapter = new CollectAdapter(R.layout.collect_item,data);
        recyclerView.setAdapter(collectAdapter);
        List<ArticleCopy> a = DataBase.getInstance().articlesDao().getAllCollect();
        collectAdapter.addData(a);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        toolbar = findViewById(R.id.toolbar);

        LinearLayoutManager linear = new LinearLayoutManager(this);
        linear.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linear);

    }


    private class CollectAdapter extends BaseQuickAdapter<ArticleCopy,BaseViewHolder> {


        public CollectAdapter(int layoutResId, @Nullable List<ArticleCopy> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ArticleCopy item) {
            helper.setText(R.id.title,item.getTitle());
            helper.setText(R.id.author,item.getAuthor());
        }
    }
}
