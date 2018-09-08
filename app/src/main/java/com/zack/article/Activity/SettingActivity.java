package com.zack.article.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.zack.article.R;
import com.zack.article.Util.ThemeConfig;

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView theme1,theme2,tvTitle,tvAuthor,tvContent;
    private SeekBar sbTitle,sbAuthor,sbContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
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

        theme2.setOnClickListener(this);
        theme1.setOnClickListener(this);
        sbAuthor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvAuthor.setText("作者字体:"+progress);
                ThemeConfig.setAuthorSize(progress,SettingActivity.this);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbTitle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvTitle.setText("标题字体:"+progress);
                ThemeConfig.setTitleSize(progress,SettingActivity.this);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbContent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvContent.setText("文章内容字体:"+progress);
                ThemeConfig.setContentSize(progress,SettingActivity.this);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        changTheme(ThemeConfig.NowTheme);
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        theme1 = findViewById(R.id.theme1);
        theme2 = findViewById(R.id.theme2);
        tvAuthor = findViewById(R.id.tv_author);
        tvContent = findViewById(R.id.tv_content);
        tvTitle = findViewById(R.id.tv_title);
        sbAuthor = findViewById(R.id.sb_author);
        sbTitle = findViewById(R.id.sb_title);
        sbContent = findViewById(R.id.sb_content);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.theme1:
                changTheme(ThemeConfig.Theme_Default);
                break;
            case R.id.theme2:
                changTheme(ThemeConfig.Theme_2);
                break;
        }
    }

    private void changTheme(int theme){
        theme1.setText("白色");
        theme2.setText("黑色");

        tvTitle.setText("标题字体:"+ThemeConfig.getTitleSize());
        tvContent.setText("文章内容字体:"+ThemeConfig.getContentSize());
        tvAuthor.setText("作者字体:"+ThemeConfig.getAuthorSize());

        sbContent.setProgress(ThemeConfig.getContentSize());
        sbTitle.setProgress(ThemeConfig.getTitleSize());
        sbAuthor.setProgress(ThemeConfig.getAuthorSize());

        switch (theme){
            case ThemeConfig.Theme_Default:
                ThemeConfig.change(this,ThemeConfig.Theme_Default);
                theme1.setText("白色（正在使用）");
                break;
            case ThemeConfig.Theme_2:
                theme2.setText("黑色（正在使用）");
                ThemeConfig.change(this,ThemeConfig.Theme_2);
                break;
        }
    }
}
