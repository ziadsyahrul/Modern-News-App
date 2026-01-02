package com.ziad.modernnewsapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    viewModel: NewsViewModel
) {
    val articles = viewModel.newsFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Modern News - Offline First") })
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            // Loading Indikator Awal
            if (articles.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(
                        count = articles.itemCount,
                        key = articles.itemKey { it.url },
                        contentType = articles.itemContentType { "article" }
                    ) { index ->
                        val item = articles[index]
                        if (item != null) {
                            NewsItem(article = item)
                        }
                    }

                    // Loading Indikator saat Scroll ke bawah (Pagination)
                    if (articles.loadState.append is LoadState.Loading) {
                        item {
                            Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            }
                        }
                    }
                }
            }
        }
    }
}