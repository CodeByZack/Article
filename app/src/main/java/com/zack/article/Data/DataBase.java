package com.zack.article.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.zack.article.App;
import com.zack.article.bean.Articles;

@Database(entities = {ArticleCopy.class}, version = 1,exportSchema = false)
public abstract class DataBase extends RoomDatabase {
    private static DataBase INSTANCE;
    public abstract ArticlesDao articlesDao();

    public static DataBase getInstance(){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(App.getContext(),DataBase.class,"movie.db").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
