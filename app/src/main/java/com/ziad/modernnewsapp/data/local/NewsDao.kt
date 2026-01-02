package com.ziad.modernnewsapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertArticles(articles: List<ArticleEntity>)

    @Query("SELECT * FROM articles")
    fun getArticles(): PagingSource<Int, ArticleEntity>

    @Query("DELETE FROM articles")
    suspend fun clearAll()
}