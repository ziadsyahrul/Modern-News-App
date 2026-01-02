package com.ziad.modernnewsapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ziad.modernnewsapp.data.local.NewsDatabase
import com.ziad.modernnewsapp.data.mapper.toArticle
import com.ziad.modernnewsapp.data.remote.NewsApi
import com.ziad.modernnewsapp.data.remote.NewsRemoteMediator
import com.ziad.modernnewsapp.domain.model.Article
import com.ziad.modernnewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDb: NewsDatabase,
    private val newsApi: NewsApi
) : NewsRepository {

    private val apiKey = "984140b0f5bd49b387c5955e4904cf0b"

    @OptIn(ExperimentalPagingApi::class)
    override fun getNewsArticles(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = NewsRemoteMediator(newsDb, newsApi, apiKey),
            pagingSourceFactory = { newsDb.dao.getArticles() }
        ).flow.map { pagingData ->
            pagingData.map { it.toArticle() }
        }
    }

    override suspend fun getSavedArticleByUrl(url: String): Article? {
        // sabar mang
        return null
    }

    override suspend fun getArticleByUrl(url: String): Article? {
        return newsDb.dao.getArticleByUrl(url)?.toArticle()
    }

}