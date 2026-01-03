package com.ziad.modernnewsapp.presentation.news_list

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
import com.ziad.modernnewsapp.domain.model.Article
import com.ziad.modernnewsapp.presentation.components.ShimmerNewsList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    viewModel: NewsViewModel,
    onArticleClick: (Article) -> Unit
) {
    val articles = viewModel.newsFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Modern News") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Logika pengecekan state Paging 3
            when (val refreshState = articles.loadState.refresh) {

                is LoadState.Loading -> {
                    // MENGGANTI CircularProgressIndicator dengan Shimmer
                    ShimmerNewsList()
                }

                is LoadState.Error -> {
                    // Tampilan jika error saat refresh (misal: internet mati & db kosong)
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Gagal memuat berita")
                        Button(onClick = { articles.retry() }) {
                            Text("Coba Lagi")
                        }
                    }
                }

                else -> {
                    // Tampilan utama jika data berhasil dimuat
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
                            articles[index]?.let { item ->
                                NewsItem(
                                    article = item,
                                    onClick = { onArticleClick(item) }
                                )
                            }
                        }

                        // Loading indikator di bawah (Pagination)
                        if (articles.loadState.append is LoadState.Loading) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(strokeWidth = 2.dp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}