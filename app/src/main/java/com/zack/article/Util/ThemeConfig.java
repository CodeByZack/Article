package com.zack.article.Util;

import android.content.Context;
import android.widget.Space;

import com.zack.article.R;

public class ThemeConfig {
    public static final String Theme = "theme";
    public static final int Theme_Default = 1;
    public static final int Theme_2 = 2;
    public static final int Theme_3 = 3;
    private static int bgColor,titleColor,authorColor,contentColor;

    public static void init(Context context){
        int theme = (int) SPUtil.getData(context,Theme,Theme_2);
        changeTheme(context, theme);
    }

    public static void change(Context context,int theme){
        changeTheme(context,theme);
        SPUtil.saveDate(context,Theme,theme);
    }

    public static int getTitleColor() {
        return titleColor;
    }

    public static int getBgColor() {

        return bgColor;
    }

    public static int getAuthorColor() {
        return authorColor;
    }

    public static int getContentColor() {
        return contentColor;
    }

    private static void changeTheme(Context context, int theme) {
        switch (theme){
            case Theme_2:
                bgColor = R.color.bg_theme1;
                titleColor = R.color.title_theme1;
                authorColor = R.color.author_theme1;
                contentColor = R.color.content_theme1;
                break;
            case Theme_3:
            default:
                bgColor = R.color.bg_light;
                titleColor = R.color.title_light;
                authorColor = R.color.author_light;
                contentColor = R.color.content_light;
                break;
        }
    }
}
