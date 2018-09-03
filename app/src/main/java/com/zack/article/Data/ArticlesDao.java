package com.zack.article.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import java.util.List;

@Dao
public interface ArticlesDao {
    /**
     * 查询所有
     *
     * @return
     */
    @Query("SELECT * FROM ArticleCopy")
    List<ArticleCopy> getAllCollect();

    /**
     * 查询所有
     *
     * @return
     */
    @Query("SELECT * FROM ArticleCopy WHERE objectId=:id ")
    List<ArticleCopy> getCollectById(String id);

    /**
     * 项数据库添加数据
     *
     * @param movie
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ArticleCopy> movie);

    /**
     * 项数据库添加数据
     *
     * @param movie
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArticleCopy movie);

    /**
     * 修改数据
     *
     * @param movie
     */
    @Update()
    void update(ArticleCopy movie);

    /**
     * 删除数据
     *
     * @param movie
     */
    @Delete()
    void delete(ArticleCopy movie);

    /**
     * 删除数据
     *
     * @param movies
     */
    @Delete()
    void delete(List<ArticleCopy> movies);
}
