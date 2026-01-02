package com.ziad.modernnewsapp.presentation.news_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ziad.modernnewsapp.domain.model.Article
import com.ziad.modernnewsapp.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: NewsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var article by mutableStateOf<Article?>(null)
        private set

    init {
        val articleUrl: String? = savedStateHandle["articleUrl"]
        articleUrl?.let {
            viewModelScope.launch {
                article = repository.getArticleByUrl(it)
            }
        }
    }
}