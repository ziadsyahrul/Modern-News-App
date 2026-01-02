package com.ziad.modernnewsapp.data.mapper

import com.ziad.modernnewsapp.data.local.ArticleEntity
import com.ziad.modernnewsapp.data.remote.dto.ArticleDto
import com.ziad.modernnewsapp.domain.model.Article

fun ArticleDto.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        url = url,
        title = title,
        description = description,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        sourceName = source.name,
        content = content
    )
}

fun ArticleEntity.toArticle(): Article {
    return Article(
        url = url,
        title = title,
        description = description,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        sourceName = sourceName,
        content = content
    )
}