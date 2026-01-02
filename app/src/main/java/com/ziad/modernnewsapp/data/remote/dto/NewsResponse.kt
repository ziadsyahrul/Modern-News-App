package com.ziad.modernnewsapp.data.remote.dto

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)