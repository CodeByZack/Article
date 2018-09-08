package com.zack.article.Util;

import android.content.Context;

import com.zack.article.R;

public class ThemeConfig {
    public static final String Theme = "theme";
    public static final String TitleSize = "TitleSize";
    public static final String ContentSize = "ContentSize";
    public static final String AuthorSize = "AuthorSize";
    public static final int Theme_Default = 1;
    public static final int Theme_2 = 2;
    public static final int Theme_3 = 3;
    public static int NowTheme = 1;
    private static int bgColor;
    private static int titleColor;
    private static int authorColor;
    private static int contentColor;
    private static int leftStartColor;
    private static int leftEndColor;
    private static int titleSize = 26,authorSize = 14,contentSize = 16;
    public static void init(Context context){
        int theme = (int) SPUtil.getData(context,Theme,Theme_2);
        titleSize = (int) SPUtil.getData(context,TitleSize,26);
        authorSize = (int) SPUtil.getData(context,AuthorSize,14);
        contentSize = (int) SPUtil.getData(context,ContentSize,16);
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
        NowTheme = theme;
        switch (theme){
            case Theme_2:
                bgColor = R.color.bg_theme1;
                titleColor = R.color.title_theme1;
                authorColor = R.color.author_theme1;
                contentColor = R.color.content_theme1;
                leftStartColor = 0xff596164;
                leftEndColor = 0xff868f96;
                break;
            case Theme_3:
            default:
                leftStartColor = 0xffe1e1e1;
                leftEndColor = 0xffffffff;
                bgColor = R.color.bg_light;
                titleColor = R.color.title_light;
                authorColor = R.color.author_light;
                contentColor = R.color.content_light;
                break;
        }
    }

    public static int getLeftStartColor() {
        return leftStartColor;
    }

    public static int getLeftEndColor() {
        return leftEndColor;
    }

    public static int getTitleSize() {
        return titleSize;
    }

    public static int getAuthorSize() {
        return authorSize;
    }

    public static int getContentSize() {
        return contentSize;
    }

    public static void setTitleSize(int titleSize,Context context) {
        ThemeConfig.titleSize = titleSize;
        SPUtil.saveDate(context,TitleSize,titleSize);
    }

    public static void setAuthorSize(int authorSize, Context context) {
        ThemeConfig.authorSize = authorSize;
        SPUtil.saveDate(context,AuthorSize,authorSize);
    }

    public static void setContentSize(int contentSize, Context context) {
        ThemeConfig.contentSize = contentSize;
        SPUtil.saveDate(context,ContentSize,contentSize);
    }
}
