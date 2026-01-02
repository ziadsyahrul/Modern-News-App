package com.ziad.modernnewsapp.domain.model

data class Article(
    val url: String,
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val content: String?,
    val publishedAt: String,
    val sourceName: String?
)