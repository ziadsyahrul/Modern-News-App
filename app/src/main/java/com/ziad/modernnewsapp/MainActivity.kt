package com.ziad.modernnewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ziad.modernnewsapp.presentation.news_detail.DetailScreen
import com.ziad.modernnewsapp.presentation.news_detail.DetailViewModel
import com.ziad.modernnewsapp.presentation.news_list.NewsScreen
import com.ziad.modernnewsapp.presentation.news_list.NewsViewModel

import com.ziad.modernnewsapp.ui.theme.ModernNewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModernNewsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsNavGraph()
                }
            }
        }
    }
}

@Composable
fun NewsNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "news_list"
    ) {
        composable(route = "news_list") {
            val viewModel: NewsViewModel = hiltViewModel()
            NewsScreen(
                viewModel = viewModel,
                onArticleClick = { article ->
                    val encodedUrl = URLEncoder.encode(article.url, StandardCharsets.UTF_8.toString())
                    navController.navigate("news_detail/$encodedUrl")
                }
            )
        }

        // Layar 2: Detail Berita
        composable(
            route = "news_detail/{articleUrl}",
            arguments = listOf(
                navArgument("articleUrl") { type = NavType.StringType }
            )
        ) {
            val detailViewModel: DetailViewModel = hiltViewModel()
            DetailScreen(
                viewModel = detailViewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}