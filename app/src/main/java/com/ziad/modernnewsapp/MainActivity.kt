package com.ziad.modernnewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import com.ziad.modernnewsapp.presentation.NewsScreen
import com.ziad.modernnewsapp.presentation.NewsViewModel

import com.ziad.modernnewsapp.ui.theme.ModernNewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            ModernNewsAppTheme {
                Surface(
                    modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // hiltViewModel() otomatis mencarikan NewsViewModel yang sudah di-inject
                    val viewModel: NewsViewModel = hiltViewModel()
                    NewsScreen(viewModel = viewModel)
                }
            }
        }
    }
}