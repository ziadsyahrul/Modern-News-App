package com.ziad.modernnewsapp.domain.repository

import androidx.paging.PagingData
import com.ziad.modernnewsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNewsArticles(): Flow<PagingData<Article>>
    suspend fun getSavedArticleByUrl(url: String): Article?
}

data class SourceDto(val id: String?, val name: String)