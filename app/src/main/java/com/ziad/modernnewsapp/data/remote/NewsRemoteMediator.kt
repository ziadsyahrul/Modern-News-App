package com.ziad.modernnewsapp.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ziad.modernnewsapp.data.local.ArticleEntity
import com.ziad.modernnewsapp.data.local.NewsDatabase
import com.ziad.modernnewsapp.data.mapper.toArticleEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator(
    private val newsDb: NewsDatabase,
    private val newsApi: NewsApi,
    private val apiKey: String
): RemoteMediator<Int, ArticleEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (state.pages.size) + 1
                    }
                }
            }

            // Ambil data dari Internet
            val response = newsApi.getNews(
                page = loadKey,
                pageSize = state.config.pageSize,
                apiKey = apiKey
            )

            // Simpan ke Database Lokal
            newsDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    newsDb.dao.clearAll()
                }
                val entities = response.articles.map { it.toArticleEntity() }
                newsDb.dao.upsertArticles(entities)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.articles.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }


}