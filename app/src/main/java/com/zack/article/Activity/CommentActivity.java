package com.zack.article.Activity;

import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vise.log.ViseLog;
import com.zack.article.Bean.Articles;
import com.zack.article.Bean.comments;
import com.zack.article.Data.DataUtils;
import com.zack.article.R;
import com.zack.article.Util.AndroidBug5497Workaround;
import com.zack.article.Util.ThemeConfig;

import java.util.LinkedList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class CommentActivity extends BaseActivity {

    private List<comments> commentDataList = new LinkedList<>();
    private CommentAdapter commentAdapter;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private LinearLayout llBg;
    private EditText inputComment;
    private TextView tvSubmit;
    private Articles articleId;

    private void applyTheme(){
        llBg.setBackgroundColor(getResources().getColor(ThemeConfig.getBgColor()));
        //todo toolbar显示问题 待修复
        bar.statusBarColor(ThemeConfig.getBgColor()).titleBar(toolbar).init();
        toolbar.setTitleTextColor(getResources().getColor(ThemeConfig.getTitleColor()));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_comment);
        //AndroidBug5497Workaround.assistActivity(this);
        articleId = (Articles) getIntent().getSerializableExtra("articleId");
        if(articleId == null){
            toast("文章ID错误！");
            finish();
        }
        initView();
        //applyTheme();
        initLogic();
        requestComments(articleId.getObjectId());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }

    private void initLogic() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        commentAdapter = new CommentAdapter(R.layout.comment_item,commentDataList);
        recyclerView.setAdapter(commentAdapter);
        commentAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                commentAdapter.loadMoreEnd();
            }
        },recyclerView);
        commentAdapter.setEmptyView(R.layout.empty_view);
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputComment.getEditableText().toString();
                final comments c = new comments();
                c.setArticleId(articleId);
                c.setContent(content);
                DataUtils.submitComment(c, new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {
                        if(e==null){
                            toast("提交成功！");
                            commentAdapter.addData(c);
                            inputComment.setText("");
                        }else{
                            toast("提交失败：" + e.getMessage());
                        }
                    }
                });
            }
        });
        toolbar.setTitle(articleId.getTitle());
        inputComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.equals("",s.toString())){
                    tvSubmit.setEnabled(true);
                    tvSubmit.setTextColor(getResources().getColor(R.color.textPrimary));
                }else{
                    tvSubmit.setTextColor(getResources().getColor(R.color.textAssistSecondary));
                    tvSubmit.setEnabled(false);
                }
            }
        });
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);
        llBg = findViewById(R.id.ll_bg);
        inputComment = findViewById(R.id.input_cpmment);
        tvSubmit = findViewById(R.id.tv_submit);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    private class CommentAdapter extends BaseQuickAdapter<comments,BaseViewHolder> {
        public CommentAdapter(int layoutResId, @Nullable List<comments> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, comments item) {
//            helper.setTextColor(R.id.name,getResources().getColor(ThemeConfig.getTitleColor()));
//            helper.setTextColor(R.id.time,getResources().getColor(ThemeConfig.getAuthorColor()));
//            helper.setTextColor(R.id.content,getResources().getColor(ThemeConfig.getContentColor()));

            helper.setText(R.id.time,item.getCreatedAt());
            helper.setText(R.id.content,item.getContent());
        }
    }

    private void requestComments(String objectId) {
        DataUtils.getCommentsByArticleId(objectId, new FindListener<comments>() {
            @Override
            public void done(List<comments> list, BmobException e) {
                if(e == null){
                    commentDataList.clear();
                    commentDataList.addAll(list);
                    commentAdapter.notifyDataSetChanged();
                }else{
                    ViseLog.d(e);
                }
            }
        });
    }
}
