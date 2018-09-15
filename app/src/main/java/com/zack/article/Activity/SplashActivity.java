package com.zack.article.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.zack.article.R;
import com.zack.article.Util.PackageUtil;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String Ver = "遇见~      版本号：v"+PackageUtil.packageName(this);
        TextView version = findViewById(R.id.version);
        version.setText(Ver);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.alpha,R.anim.alpha_out);
                finish();
            }
        },1000);
    }
}
