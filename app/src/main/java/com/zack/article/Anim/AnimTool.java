package com.zack.article.Anim;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.vise.log.ViseLog;
import com.zack.article.R;

public class AnimTool {
    private static Animation rotate = null;
    private static boolean isFirstRotate = true;
    private static boolean cancelRotate = false;

    public static void startRotate(final View v){
        if(rotate == null) rotate = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotate);
        v.startAnimation(rotate);
        v.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(!cancelRotate) {
                    v.startAnimation(rotate);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static void stopRotate(View v){
        if(v.getAnimation() == null) return;
        //确保执行一次完整的动画
        if(isFirstRotate){
            cancelRotate = true;
        }else{
            v.clearAnimation();
        }
    }
}
